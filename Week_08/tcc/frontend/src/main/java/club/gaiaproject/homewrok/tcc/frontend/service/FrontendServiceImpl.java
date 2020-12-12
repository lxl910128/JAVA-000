package club.gaiaproject.homewrok.tcc.frontend.service;

import club.gaiaproject.homewrok.tcc.frontend.client.BackendAClient;
import club.gaiaproject.homewrok.tcc.frontend.client.BackendBClient;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Phoenix Luo
 * @version 2020/12/10
 **/
@Service
@Slf4j
public class FrontendServiceImpl implements FrontendService {
    @Autowired
    private BackendAClient backendAClient;
    
    @Autowired
    private BackendBClient backendBClient;
    
    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public String buy() {
        log.info("--------------");
        log.info("改变订单状态为UPDATING! 如果状态改变失败直接回滚 跳出");
        
        // 冻结10块钱
        backendAClient.transfer();
        // 准备一件商品
        backendBClient.outbound();
        log.info("--------------");
        return "success";
    }
    
    public void confirmOrderStatus() {
        // confirm失败则会重试，如果一直失败理论需要人工干预
        log.info("--------------");
        log.info("冻结资金，存货检查完成，幂等修改订单状态为 交易成功");
        log.info("--------------");
    }
    
    public void cancelOrderStatus() {
        log.info("--------------");
        log.info("冻结资金，存货检查失败，幂等修改订单状态为 交易失败");
        log.info("--------------");
    }
}
