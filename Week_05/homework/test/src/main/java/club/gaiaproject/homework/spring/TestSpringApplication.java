package club.gaiaproject.homework.spring;

import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestSpringApplication {

    public static void main(String[] args) {
        // bean factory
        FileSystemXmlApplicationContext fileContext = new FileSystemXmlApplicationContext("classpath:spring-bean.xml");

        // 从 factoryBean 获取构造bean
        fileContext.getBean("person", Person.class).hello();

        // 拿xml 构造的bean
        fileContext.getBean("programmer", Person.class).hello();

        // 拿 @Component 构造的bean
        fileContext.getBean("boss", Person.class).hello();

        // 拿 @Bean 构造的bean ，该bean 需要属性self-realization为true
        if (fileContext.containsBean("me")) {
            fileContext.getBean("me", Person.class).hello();
        }

    }

}
