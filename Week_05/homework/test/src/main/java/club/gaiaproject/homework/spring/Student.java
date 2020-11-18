package club.gaiaproject.homework.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Person {
    private String name;
    private String age;

    @Override
    public void hello() {
        System.out.println("hello! my name is " + name);
    }
}
