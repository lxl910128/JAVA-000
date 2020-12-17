package io.kimmking.rpcfx.util;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.error.RpcfxException;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;
import org.springframework.beans.BeanUtils;

import java.net.URI;
import java.nio.charset.Charset;

import static io.kimmking.rpcfx.client.Rpcfx.RpcfxInvocationHandler.JSONTYPE;
import static io.netty.buffer.Unpooled.wrappedBuffer;

/**
 * @author Phoenix Luo
 * @version 2020/12/17
 * <p>
 * 枚举单例
 **/
public enum NettyHttpClient {
    INSTANCE;
    
    
    public RpcfxResponse post(String url, RpcfxRequest rpcRequest) {
        RpcfxResponse ret = new RpcfxResponse();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup).channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, false);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new HttpObjectAggregator(1048576));
                    ch.pipeline().addLast(new HttpClientMsgHandler(ret));
                    
                }
            });
            URI uri = new URI(url);
            String host = uri.getHost();
            int port = uri.getPort();
            // Make the connection attempt.
            Channel ch = bootstrap.connect(host, port).sync().channel();
            // Prepare the HTTP request.
            String bodyStr = JSON.toJSONString(rpcRequest);
            FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST, uri.getRawPath(), wrappedBuffer(bodyStr.getBytes(CharsetUtil.UTF_8)));
            request.headers().set(HttpHeaderNames.HOST, host);
            request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
            request.headers().set(HttpHeaderNames.CONTENT_TYPE, JSONTYPE);
            // 务必不要忘了 length
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH, bodyStr.length());
            // Send the HTTP request.
            ch.writeAndFlush(request);
            
            // Wait for the server to close the connection.
            ch.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RpcfxException("远程调用失败！", e);
        } finally {
            workerGroup.shutdownGracefully();
        }
        return ret;
    }
    
    class HttpClientMsgHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
        
        private RpcfxResponse response;
        
        public HttpClientMsgHandler(RpcfxResponse response) {
            this.response = response;
        }
        
        @Override
        
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
        
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse response) throws Exception {
            if (!response.headers().isEmpty()) {
                for (CharSequence name : response.headers().names()) {
                    for (CharSequence value : response.headers().getAll(name)) {
                        System.err.println("HEADER: " + name + " = " + value);
                    }
                }
                System.err.println();
            }
            RpcfxResponse rpc = JSON.parseObject(response.content().toString(Charset.forName("utf-8")), RpcfxResponse.class);
            BeanUtils.copyProperties(rpc, this.response);
        }
    }
    
}
