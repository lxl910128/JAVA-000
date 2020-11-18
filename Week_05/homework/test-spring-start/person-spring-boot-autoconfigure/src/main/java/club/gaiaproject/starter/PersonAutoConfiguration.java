package club.gaiaproject.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Phoenix Luo
 * @version 2020/11/18
 **/
@Configuration
@ConditionalOnProperty(value = "person.enable", havingValue = "true")
public class PersonAutoConfiguration {
    @Bean
    public Joker getJoker(PersonConfigure configure) {
        return new Joker(configure.getName());
    }
}
