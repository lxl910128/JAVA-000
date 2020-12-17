package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.error.RpcfxException;

/**
 * @author Phoenix Luo
 * @version 2020/12/16
 **/
public class ReflexResolver implements RpcfxResolver {
    @Override
    public Object resolve(String serviceClass) {
        try {
            // 通过反射拿到所有 实现类
            return Class.forName(serviceClass).newInstance();
        } catch (Exception e) {
            throw new RpcfxException("实心对象未找到", e);
        }
        
    }
}
