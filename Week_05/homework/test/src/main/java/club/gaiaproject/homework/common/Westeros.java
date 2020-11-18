package club.gaiaproject.homework.common;

import club.gaiaproject.homework.spring.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class Westeros {
    @PostConstruct
    public void init() {
        System.out.println("混乱不是深渊是阶梯！");
    }

    @Bean(name = "petyr")
    public Person getPetyr() {
        return new Person() {
            @Override
            public void hello() {
                System.out.println("hello I'm petyr");
            }
        };
    }
}
