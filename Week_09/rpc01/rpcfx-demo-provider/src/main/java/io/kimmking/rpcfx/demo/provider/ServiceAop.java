package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.error.RpcfxException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Phoenix Luo
 * @version 2020/12/16
 **/
@Aspect
@Component
public class ServiceAop {
    // 切包中所有类及方法
    @Pointcut("execution(* io.kimmking.rpcfx.server.*.*(..))")
    public void pointcut() {
    }
    
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (RpcfxException e) {
            RpcfxResponse response = new RpcfxResponse();
            response.setException(e.getException());
            response.setStatus(false);
            return response;
        }
    }
    
    
}
