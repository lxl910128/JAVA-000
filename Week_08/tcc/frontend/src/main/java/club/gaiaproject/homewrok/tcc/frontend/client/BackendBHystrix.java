package club.gaiaproject.homewrok.tcc.frontend.client;

import org.springframework.stereotype.Component;

/**
 * @author Phoenix Luo
 * @version 2020/12/10
 **/
@Component
public class BackendBHystrix implements BackendBClient {
    @Override
    public String outbound() {
        System.out.println("B 出库失败。。");
        return "error";
    }
}
