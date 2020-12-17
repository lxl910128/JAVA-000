package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.error.RpcfxException;
import org.reflections.Reflections;

import java.util.Set;

/**
 * @author Phoenix Luo
 * @version 2020/12/16
 **/
public class ReflexResolver implements RpcfxResolver {
    
    @Override
    public Object resolve(String serviceClass) {
        // 通过反射拿到所有 实现类
        try {
            // 扫描当前包下的类找出 serviceClass接口的实现
            Reflections reflections = new Reflections(this.getClass().getPackage().getName());
            Class clazz = Class.forName(serviceClass);
            Set<Class<?>> monitorClasses = reflections.getSubTypesOf(clazz);
            // 多个实现取第一个
            if (monitorClasses.size() > 0) {
                return monitorClasses.iterator().next().newInstance();
            }
            throw new RpcfxException("实现对象未找到");
        } catch (Exception e) {
            throw new RpcfxException("实现对象未找到", e);
        }
        
    }
}
