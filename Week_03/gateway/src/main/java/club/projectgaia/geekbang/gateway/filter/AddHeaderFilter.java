package club.projectgaia.geekbang.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
@Component
@Slf4j
public class AddHeaderFilter implements HttpRequestFilter {
    @Override
    public Boolean filter(FullHttpRequest fullRequest) {
        fullRequest.headers().add("nio", "Phoenix");
        return true;
    }
}
