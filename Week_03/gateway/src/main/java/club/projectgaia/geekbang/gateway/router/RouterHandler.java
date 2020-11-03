package club.projectgaia.geekbang.gateway.router;

import club.projectgaia.geekbang.gateway.ServiceProperties;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
@Component
@Slf4j
public class RouterHandler {
    @Autowired
    private ServiceProperties routes;

    public String route(FullHttpRequest httpRequest) {
        String uri = httpRequest.uri();
        log.info("请求uri:{}", uri);
        return routes.getRouter().get(uri.substring(1));
    }
}
