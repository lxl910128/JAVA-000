package io.kimmking.rpcfx.demo.consumer.aop;

import com.thoughtworks.xstream.XStream;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.error.RpcfxException;
import io.kimmking.rpcfx.util.NettyHttpClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author Phoenix Luo
 * @version 28020/12/17
 **/
@Aspect
@Component
public class ClientAOP {
    private final XStream xstream = new XStream();
    
    @Pointcut("execution(* io.kimmking.rpcfx.demo.api.server.*.*(..))")
    public void pointcut() {
    }
    
    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        try {
            
            RpcfxRequest request = new RpcfxRequest();
            String interfaceName = joinPoint.getTarget().getClass().getGenericInterfaces()[0].getTypeName();
            request.setServiceClass(interfaceName);
            request.setMethod(joinPoint.getSignature().getName());
            request.setParams(joinPoint.getArgs());
            String url;
            switch (interfaceName) {
                case "io.kimmking.rpcfx.demo.api.server.UserService":
                    url = "http://localhost:8080/";
                    break;
                case "io.kimmking.rpcfx.demo.api.server.OrderService":
                    url = "http://localhost:8080/";
                    break;
                default:
                    url = "http://localhost:8080/";
            }
            /*
            Object service = joinPoint.getThis();
            Method method = service.getClass().getMethod("url");
            String url = (String) method.invoke(joinPoint.getTarget());
            */
            
            RpcfxResponse response = NettyHttpClient.INSTANCE.post(url, request);
            if (!response.isStatus()) {
                if (response.getException() != null) {
                    throw new RpcfxException(response.getResult().toString(), response.getException());
                }
            }
            return xstream.fromXML(response.getResult().toString());
        } catch (RpcfxException e) {
            RpcfxResponse response = new RpcfxResponse();
            response.setResult(e.getMessage());
            response.setException(e.getException());
            response.setStatus(false);
            return response;
        } catch (Exception e) {
            RpcfxResponse response = new RpcfxResponse();
            response.setResult(e.getMessage());
            response.setException(e);
            response.setStatus(false);
            return response;
        }
    }
}
