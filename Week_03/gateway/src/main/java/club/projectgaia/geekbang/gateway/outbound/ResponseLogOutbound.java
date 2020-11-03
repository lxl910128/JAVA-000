package club.projectgaia.geekbang.gateway.outbound;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
@Slf4j
public class ResponseLogOutbound extends ChannelOutboundHandlerAdapter {


    @Override
    public void write(final ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("接到请求！");
        super.write(ctx, msg, promise);


    }


}
