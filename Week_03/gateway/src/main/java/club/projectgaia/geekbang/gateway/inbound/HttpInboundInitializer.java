package club.projectgaia.geekbang.gateway.inbound;

import club.projectgaia.geekbang.gateway.outbound.ResponseLogOutbound;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();
       /* if (sslCtx != null) {
            p.addLast(sslCtx.newHandler(ch.alloc()));
        }*/
        p.addLast("coder", new HttpServerCodec());
        //p.addLast(new HttpServerExpectContinueHandler());
        p.addLast("fullHttp", new HttpObjectAggregator(1024 * 1024));
        p.addLast("filter", new FilterInbound());
        p.addLast("router", new RouterInbound());
        p.addLast("ret", new ResponseLogOutbound());
        p.addLast("client", new HttpClientInbound());

    }
}
