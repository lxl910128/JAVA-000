package club.gaiaproject.homework.tcc.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Phoenix Luo
 * @version 2020/11/28
 **/
@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RegistryApplication.class, args);
    }
}
