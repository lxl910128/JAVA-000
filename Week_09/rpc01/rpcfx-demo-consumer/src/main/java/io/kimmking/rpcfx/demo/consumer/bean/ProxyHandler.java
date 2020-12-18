package io.kimmking.rpcfx.demo.consumer.bean;

import io.kimmking.rpcfx.demo.api.server.UserService;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author Phoenix Luo
 * @version 2020/12/18
 **/
@Component
public class ProxyHandler {
    //生成的代理对象名称前缀
    private static final String PROXYFREFIX = "$Proxy";
    //生成的代理对象名称前缀
    private static final String PROXYSUFFIX = "Impl";
    
    //生成代理对象
    public <T> T getProxyOject(final Class<T> serviceClass) {
        T proxyObject = null;
        try {
            ClassPool pool = ClassPool.getDefault();
            //创建代理类对象
            CtClass ctClass = pool.makeClass(getPackageName(serviceClass) + getProxyObjectName(serviceClass));
            
            //设置代理类的接口
            //获取代理对象的接口类
            CtClass interf = pool.getCtClass(getPackageName(serviceClass) + "." + serviceClass.getSimpleName());
            CtClass[] interfaces = new CtClass[]{interf};
            ctClass.setInterfaces(interfaces);
            //代理类的所有方法
            CtMethod[] methods = interf.getDeclaredMethods();
            for (CtMethod method : methods) {
                String methodName = method.getName();
                CtMethod cm = new CtMethod(method.getReturnType(), methodName, method.getParameterTypes(), ctClass);
                cm.setBody("{\n System.out.println(\"hand up my homework from proxy Object\"); \n return null;\n }");
                ctClass.addMethod(cm);
            }
            Class aClass = ctClass.toClass();
            proxyObject = (T) aClass.newInstance();
        } catch (NotFoundException | CannotCompileException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return proxyObject;
    }
    
    //获取包名
    private String getPackageName(final Class t) {
        Package aPackage = t.getPackage();
        String packageName = aPackage.getName();
        return packageName;
    }
    
    //获取代理对象的名称
    private String getProxyObjectName(final Class t) {
        return PROXYFREFIX + t.getSimpleName() + PROXYSUFFIX;
    }
    
    
}
