package club.gaiaproject.homewrok.tcc.frontend.client;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Phoenix Luo
 * @version 2020/12/10
 **/
@FeignClient(value = "backend-a", fallback = BackendAHystrix.class)
public interface BackendAClient {
    @Hmily
    @RequestMapping("/backend/transfer")
    String transfer();
}
