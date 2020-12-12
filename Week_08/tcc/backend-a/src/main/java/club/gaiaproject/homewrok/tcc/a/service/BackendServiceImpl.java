package club.gaiaproject.homewrok.tcc.a.service;

import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Phoenix Luo
 * @version 2020/12/10
 **/
@Service
@Slf4j
public class BackendServiceImpl implements BackendService {
    
    private Random random = new Random();
    
    @Override
    @HmilyTCC(confirmMethod = "transferConfrim", cancelMethod = "transferCancel")
    public String transfer() {
        log.info("--------------");
        Integer r = random.nextInt(10);
        log.info("账户系统try阶段，冻结用户10块钱  随机数{}", r);
        
        if (r == 7) {
            log.info("账户系统 模拟 try失败！");
            throw new RuntimeException("账户系统 try失败！");
        } else if (r == 8) {
            log.info("账户系统 模拟 超时！");
            try {
                Thread.sleep(8000);
            } catch (Exception e) {
            
            }
        }
        log.info("--------------");
        return "冻结10块钱成功";
    }
    
    public void transferConfrim() {
        log.info("--------------");
        Integer r = random.nextInt(10);
        log.info("账户系统 confrim 阶段");
        log.info("幂等 事务提交 解冻10块，扣款10块 随机数：{}", r);
        
        if (r == 7) {
            log.info("需要人工干预");
            log.info("账户系统  confrim 阶段 模拟 confrim！");
            throw new RuntimeException("账户系统 confrim！");
        } else if (r == 8) {
            log.info("需要人工干预");
            log.info("账户系统  confrim 阶段 模拟 超时！");
            try {
                Thread.sleep(8000);
            } catch (Exception e) {
            
            }
        }
        log.info("--------------");
    }
    
    public void transferCancel() {
        log.info("--------------");
        Integer r = random.nextInt(10);
        log.info("账户系统 Cancel 阶段");
        log.info("幂等 事务提交 解冻10块 随机数:{}", r);
        
        if (r == 7) {
            log.info("需要人工干预");
            log.info("账户系统 Cancel 阶段 模拟 Cancel！");
            throw new RuntimeException("账户系统 Cancel！");
        } else if (r == 8) {
            log.info("需要人工干预");
            log.info("账户系统 Cancel 阶段 模拟 超时！");
            try {
                Thread.sleep(8000);
            } catch (Exception e) {
            
            }
        }
        log.info("--------------");
    }
}
