package club.gaiaproject.homework.spring;

import club.gaiaproject.starter.Joker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {

    @Autowired
    private Joker joker;

    public static void main(String[] args) {
        // 通过spring.factores 识别 westeros 和 gaia 并控制加载顺序
        SpringApplication.run(TestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        joker.say();
    }
}
