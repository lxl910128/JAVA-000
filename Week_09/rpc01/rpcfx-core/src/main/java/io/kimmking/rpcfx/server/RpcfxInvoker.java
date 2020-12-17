package io.kimmking.rpcfx.server;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.error.RpcfxException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RpcfxInvoker {
    private XStream xstream = new XStream(new StaxDriver());
    private RpcfxResolver resolver;
    
    public RpcfxInvoker(RpcfxResolver resolver) {
        this.resolver = resolver;
    }
    
    public RpcfxResponse invoke(RpcfxRequest request) {
        RpcfxResponse response = new RpcfxResponse();
        String serviceClass = request.getServiceClass();
        
        // 作业1：改成泛型和反射
        // 2.封装一个统一的RpcfxException
        // 客户端也需要判断异常
        Object service = resolver.resolve(serviceClass);
        try {
            Method method = resolveMethodFromClass(service.getClass(), request.getMethod());
            Object result = method.invoke(service, request.getParams());
            // 3.Xstream
            response.setResult(xstream.toXML(result));
            response.setStatus(true);
            return response;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RpcfxException("RPC服务调用失败", e);
        }
    }
    
    private Method resolveMethodFromClass(Class<?> klass, String methodName) {
        return Arrays.stream(klass.getMethods()).filter(m -> methodName.equals(m.getName())).findFirst().get();
    }
    
}
