package club.gaiaproject.homework.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author Phoenix Luo
 * @version 2020/11/17
 **/
public class ProxyTest {
    public static void main(String[] args) {
        // 常规，静态代理
        Person person = new PersonStaticProxy();
        person.say("ghostInTheShell!");

        //动态代理： 不用创建接口实现类，直接代码实现
        InvocationHandler handler = (proxy, method, args1) -> {
            System.out.println("动态代理实现方法:" + method.getName());
            if (method.getName().equals("say")) {
                System.out.println("Proxy: hello word! " + args1[0]);
            }
            return null;
        };
        Person proxyInstance = (Person) Proxy.newProxyInstance(
                // 传入ClassLoader
                ProxyTest.class.getClassLoader(),
                // 传入要实现的接口
                new Class[]{Person.class},
                // 传入处理调用方法的InvocationHandler
                handler);
        proxyInstance.say("ghostInTheShell");


        //动态代理： 增强已有实现类
        InvocationHandler handlerUp = (proxy, method, args1) -> {
            System.out.println("动态代理增强方法:" + method.getName());
            method.invoke(person, args1[0]);
            System.out.println("增强结束");
            return null;
        };
        ((Person) Proxy.newProxyInstance(
                // 传入ClassLoader
                person.getClass().getClassLoader(),
                // 传入要实现的接口
                new Class[]{Person.class},
                // 传入处理调用方法的InvocationHandler
                handlerUp)).say("ghostInTheShell");
    }
}
