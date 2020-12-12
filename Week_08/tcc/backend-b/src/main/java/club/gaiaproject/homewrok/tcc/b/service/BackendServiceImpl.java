package club.gaiaproject.homewrok.tcc.b.service;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

/**
 * @author Phoenix Luo
 * @version 2020/12/10
 **/
@Service
@Slf4j
public class BackendServiceImpl implements BackendService {
    
    @Override
    @HmilyTCC(confirmMethod = "inventoryConfrim", cancelMethod = "inventoryCancel")
    public String outbound() {
        log.info("库存try阶段，检查库存数是否足够，冻结1份商品");
        return "冻结1个商品";
    }
    
    public void inventoryConfrim() {
        log.info("库存 confrim 阶段，库存实际减1");
    }
    
    public void inventoryCancel() {
        log.info("库存 Cancel 阶段，取消冻结商品");
    }
    
}
