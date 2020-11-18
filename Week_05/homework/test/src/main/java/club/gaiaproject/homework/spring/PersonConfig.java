package club.gaiaproject.homework.spring;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
//被@ComponentScan自动扫描进去，无法控制顺序
//使用META-INF/spring.factories配置扫描时才有用
@AutoConfigureBefore(Boss.class)
public class PersonConfig {
    @PostConstruct
    public void init() {
        System.out.println("初始化 PersonConfig!");
    }

    // 属性为XX时构造
    @ConditionalOnProperty(value = "self-realization", havingValue = "true")
    // 构造时调用 init 方法
    @Bean(name = "me", initMethod = "init")
    public Person getMe() {
        return new Programmer("Phoenix");
    }

}
