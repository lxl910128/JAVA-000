package club.projectgaia.geekbang.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
@Component
@Slf4j
public class AuthorizationFilter implements HttpRequestFilter {
    @Override
    public Boolean filter(FullHttpRequest fullRequest) {
        HttpHeaders allHeader = fullRequest.headers();
        Iterator<Map.Entry<String, String>> it = allHeader.iteratorAsString();
        while (it.hasNext()) {
            Map.Entry<String, String> header = it.next();
            log.info("header {} -> {}", header.getKey(), header.getValue());
        }
        // 永远为true
        return true;
    }
}
