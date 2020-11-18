package club.gaiaproject.homework.spring;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

@Data
public class PersonFactory implements FactoryBean<Person> {

    private String type;

    @Override
    public Person getObject() throws Exception {
        if ("student".equals(type)) {
            return new Student("tom", "12");
        } else {
            return new Programmer();
        }
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
