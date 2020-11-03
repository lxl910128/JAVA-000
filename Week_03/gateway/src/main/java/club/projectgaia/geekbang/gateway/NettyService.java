package club.projectgaia.geekbang.gateway;

import club.projectgaia.geekbang.gateway.inbound.HttpInboundInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
@Component
@Slf4j
public class NettyService {
    @Value("${netty.bosses}")
    private Integer bosses;

    @Value("${netty.workers}")
    private Integer workers;

    @Value("${netty.port}")
    private Integer port;

    public void run() {
        log.info("netty boss:{}, worker:{}, port:{}", bosses, workers, port);

        //处理连接接入的线程
        EventLoopGroup bossGroup = new NioEventLoopGroup(bosses);
        //处理连接数据读写逻辑的线程
        EventLoopGroup workGroup = new NioEventLoopGroup(workers);

        try {
            //netty 初始化
            ServerBootstrap server = new ServerBootstrap();
            server.option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .option(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            server.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HttpInboundInitializer());

            Channel ch = server.bind(port).sync().channel();
            log.info("启动netty");
            ch.closeFuture().sync();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
