package club.gaiaproject.homework.source.aop;

import club.gaiaproject.homework.source.common.DatabaseContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Phoenix Luo
 * @version 2020/11/30
 **/
@Aspect
@Component
@Slf4j
public class ServiceAOP {
    @Pointcut("@annotation(club.gaiaproject.homework.source.common.ReadOnly)")
    public void readOnly() {
    }
    
    @Before("readOnly()")
    public void doBefore() {
        DatabaseContextHolder.setDbType("slave");
    }
    
}
