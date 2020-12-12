package club.gaiaproject.homewrok.tcc.a.service;

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
    @HmilyTCC(confirmMethod = "transferConfrim", cancelMethod = "transferCancel")
    public String transfer() {
        log.info("账户系统try阶段，冻结用户10块钱");
        return "冻结10块钱成功";
    }
    
    public void transferConfrim() {
        log.info("账户系统 confrim 阶段");
        log.info("幂等 事务提交 解冻10块，扣款10块");
    }
    
    public void transferCancel() {
        log.info("账户系统 Cancel 阶段");
        log.info("幂等 事务提交 解冻10块");
    }
}
