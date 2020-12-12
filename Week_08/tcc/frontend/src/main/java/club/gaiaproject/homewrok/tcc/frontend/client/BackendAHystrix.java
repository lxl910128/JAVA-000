package club.gaiaproject.homewrok.tcc.frontend.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author Phoenix Luo
 * @version 2020/12/10
 **/
@Component
@Slf4j
public class BackendAHystrix implements BackendAClient {
    
    @Override
    public String transfer() {
        log.warn("A:转账失败！");
        return "error";
    }
}
