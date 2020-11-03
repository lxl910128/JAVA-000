package club.projectgaia.geekbang.gateway.common.util;

import club.projectgaia.geekbang.gateway.common.Consts;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
public abstract class ResponseUtils {
    public static FullHttpResponse buildSuccess(String body) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body.getBytes()));
        response.headers().set("Content-Type", "application/json");
        response.headers().setInt("Content-Length", body.length());
        return response;
    }

    public static FullHttpResponse buildError() {
        return buildSuccess(Consts.ERROR_RESPONSE);
    }
}
