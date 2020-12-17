package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResolver;
import io.kimmking.rpcfx.api.RpcfxResponse;
import io.kimmking.rpcfx.server.RpcfxInvoker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAspectJAutoProxy
public class RpcfxServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RpcfxServerApplication.class, args);
    }
    
    @Autowired
    RpcfxInvoker invoker;
    
    @PostMapping("/")
    public RpcfxResponse invoke(@RequestBody RpcfxRequest request) {
        return invoker.invoke(request);
    }
    
    @Bean
    public RpcfxInvoker createInvoker(@Autowired RpcfxResolver resolver) {
        return new RpcfxInvoker(resolver);
    }
    
    @Bean
    public RpcfxResolver createResolver() {
        //return new DemoResolver();
        return new ReflexResolver();
    }
    /*
    // 能否去掉name
    @Bean(name = "io.kimmking.rpcfx.demo.api.UserService")
    public UserService createUserService() {
        return new UserServiceImpl();
    }
    
    @Bean(name = "io.kimmking.rpcfx.demo.api.OrderService")
    public OrderService createOrderService() {
        return new OrderServiceImpl();
    }
    */
}
