package club.projectgaia.geekbang.gateway.inbound;

import club.projectgaia.geekbang.gateway.client.HttpClientService;
import club.projectgaia.geekbang.gateway.common.Consts;
import club.projectgaia.geekbang.gateway.common.GatewayStatus;
import club.projectgaia.geekbang.gateway.common.util.ResponseUtils;
import club.projectgaia.geekbang.gateway.common.util.SpringUtils;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.util.Attribute;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.protocol.HTTP;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
public class HttpClientInbound extends ChannelInboundHandlerAdapter {

    private HttpClientService service;

    public HttpClientInbound() {
        this.service = SpringUtils.getBean(HttpClientService.class);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object fullRequest) throws Exception {
        Attribute<GatewayStatus> status = ctx.channel().attr(Consts.GATEWAY_STATUS);
        // 校验status
        if (checkStatus(status)) {
            // 返回正确
            service.handler(ctx, status.get().getUrl(), HttpUtil.isKeepAlive((FullHttpRequest) fullRequest));
        } else {
            // 返回错误
            FullHttpResponse response = ResponseUtils.buildError();
            if (!HttpUtil.isKeepAlive((FullHttpRequest) fullRequest)) {
                ctx.write(response).addListener(ChannelFutureListener.CLOSE);
            } else {
                response.headers().set(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
                ctx.write(response);
            }
            ctx.flush();
        }

    }

    private boolean checkStatus(Attribute<GatewayStatus> statusCtx) {
        if (statusCtx.get() != null && StringUtils.isNotBlank(statusCtx.get().getUrl()) && statusCtx.get().getFilter()) {
            return true;
        } else {
            return false;
        }

    }
}
