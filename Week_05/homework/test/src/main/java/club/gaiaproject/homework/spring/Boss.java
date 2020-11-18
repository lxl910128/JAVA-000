package club.gaiaproject.homework.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Boss implements Person {

    @Override
    public void hello() {
        System.out.println("I'm your boss, Obey me!");
    }

    @PostConstruct
    public void init() {
        System.out.println("I have a good idea!");
    }
}
