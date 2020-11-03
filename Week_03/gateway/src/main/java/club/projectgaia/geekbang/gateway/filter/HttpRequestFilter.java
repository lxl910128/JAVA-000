package club.projectgaia.geekbang.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;

public interface HttpRequestFilter {

    // true 没问题 false 有问题
    Boolean filter(FullHttpRequest fullRequest);

}
