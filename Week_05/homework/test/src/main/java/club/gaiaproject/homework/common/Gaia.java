package club.gaiaproject.homework.common;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@AutoConfigureAfter(Westeros.class)
public class Gaia {
    @PostConstruct
    public void init() {
        System.out.println("命运共同体！");
    }
}
