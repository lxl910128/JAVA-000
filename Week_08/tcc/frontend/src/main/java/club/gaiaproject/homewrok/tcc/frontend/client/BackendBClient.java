package club.gaiaproject.homewrok.tcc.frontend.client;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Phoenix Luo
 * @version 2020/12/10
 **/
@FeignClient(value = "backend-b", fallback = BackendBHystrix.class)
public interface BackendBClient {
    @Hmily
    @RequestMapping("/backend/outbound")
    String outbound();
}
