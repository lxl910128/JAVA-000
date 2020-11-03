package club.projectgaia.geekbang.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Phoenix Luo
 * @version 2020/11/3
 **/
@SpringBootApplication
@EnableConfigurationProperties
public class GatewayApplication implements CommandLineRunner {
    @Autowired
    private NettyService nettyService;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        nettyService.run();
    }
}
