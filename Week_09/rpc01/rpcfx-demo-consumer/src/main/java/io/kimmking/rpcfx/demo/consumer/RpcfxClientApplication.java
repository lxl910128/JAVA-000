package io.kimmking.rpcfx.demo.consumer;

import io.kimmking.rpcfx.demo.api.domain.Order;
import io.kimmking.rpcfx.demo.api.domain.User;
import io.kimmking.rpcfx.demo.api.server.OrderService;
import io.kimmking.rpcfx.demo.api.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class RpcfxClientApplication implements CommandLineRunner {
    
    // 二方库
    // 三方库 lib
    // nexus, userserivce -> userdao -> user
    //
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;
    
    public static void main(String[] args) {
        
        
        SpringApplication.run(RpcfxClientApplication.class, args);
    }
    
    @Bean
    public UserService getUserService() {
        return new UserService() {
            @Override
            public User findById(int id) {
                return null;
            }
        };
    }
    
    @Bean
    public OrderService getOrderService() {
        return new OrderService() {
            @Override
            public Order findOrderById(int id) {
                return null;
            }
        };
    }
    
    @Override
    public void run(String... args) throws Exception {
        // UserService service = new xxx();
        // service.findById
        
        //UserService userService = Rpcfx.create(UserService.class, "http://localhost:8080/");
        User user = userService.findById(1);
        System.out.println("find user id=1 from server: " + user.getName());
        
        //OrderService orderService = Rpcfx.create(OrderService.class, "http://localhost:8080/");
        Order order = orderService.findOrderById(1992129);
        System.out.println(String.format("find order name=%s, amount=%f", order.getName(), order.getAmount()));
        
        // 新加一个OrderService
    }
}
