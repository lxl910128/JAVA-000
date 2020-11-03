package club.projectgaia.geekbang.gateway.inbound;

import club.projectgaia.geekbang.gateway.common.Consts;
import club.projectgaia.geekbang.gateway.common.GatewayStatus;
import club.projectgaia.geekbang.gateway.common.util.SpringUtils;
import club.projectgaia.geekbang.gateway.router.RouterHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.Attribute;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
public class RouterInbound extends ChannelInboundHandlerAdapter {

    private RouterHandler routerHandler;

    public RouterInbound() {
        this.routerHandler = SpringUtils.getBean(RouterHandler.class);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String relUri = routerHandler.route((FullHttpRequest) msg);
        if (StringUtils.isNotBlank(relUri)) {
            Attribute<GatewayStatus> statusCtx = ctx.channel().attr(Consts.GATEWAY_STATUS);
            statusCtx.get().setUrl(relUri);
        }
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
