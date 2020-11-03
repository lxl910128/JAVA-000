package club.projectgaia.geekbang.gateway.inbound;

import club.projectgaia.geekbang.gateway.common.Consts;
import club.projectgaia.geekbang.gateway.common.GatewayStatus;
import club.projectgaia.geekbang.gateway.common.util.SpringUtils;
import club.projectgaia.geekbang.gateway.filter.AllRequestFilter;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.Attribute;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
public class FilterInbound extends ChannelInboundHandlerAdapter {
    private AllRequestFilter allRequestFilter;

    public FilterInbound() {
        this.allRequestFilter = SpringUtils.getBean(AllRequestFilter.class);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Boolean filterFlag = allRequestFilter.doFilter((FullHttpRequest) msg);
        Attribute<GatewayStatus> statusCtx = ctx.channel().attr(Consts.GATEWAY_STATUS);
        GatewayStatus status = new GatewayStatus();
        status.setFilter(filterFlag);
        statusCtx.setIfAbsent(status);

        ctx.fireChannelRead(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            ctx.close();
        }
    }
}
