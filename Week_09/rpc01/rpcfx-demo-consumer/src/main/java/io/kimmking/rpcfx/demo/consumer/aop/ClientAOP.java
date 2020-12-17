package io.kimmking.rpcfx.demo.consumer.aop;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.demo.api.domain.User;
import io.kimmking.rpcfx.error.RpcfxException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Phoenix Luo
 * @version 2020/12/17
 **/
@Aspect
@Component
public class ClientAOP {
    @Pointcut("execution(* io.kimmking.rpcfx.demo.api.server.*.*(..))")
    public void pointcut() {
    }
    
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(joinPoint.getTarget().getClass().getGenericInterfaces()[0].getTypeName());
            request.setMethod(joinPoint.getSignature().getName());
            request.setParams(joinPoint.getArgs());
            
            
            return new User();
        } catch (RpcfxException e) {
            RpcfxResponse response = new RpcfxResponse();
            response.setResult(e.getMessage());
            response.setException(e.getException());
            response.setStatus(false);
            return response;
        }
    }
}
