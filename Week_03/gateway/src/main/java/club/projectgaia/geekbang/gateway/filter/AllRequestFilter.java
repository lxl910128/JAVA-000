package club.projectgaia.geekbang.gateway.filter;

import io.netty.handler.codec.http.FullHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
@Component
public class AllRequestFilter {
    @Autowired
    List<HttpRequestFilter> allFilter;

    // false 有问题 true 没问题
    public Boolean doFilter(FullHttpRequest fullRequest) {
        for (HttpRequestFilter filter : allFilter) {
            if (!filter.filter(fullRequest)) {
                return false;
            }
        }
        return true;
    }
}
