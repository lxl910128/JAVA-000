package club.gaiaproject.homework.spring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Programmer implements Person {
    private String name;

    @Override
    public void hello() {
        System.out.println(this.name + " say, ghost in the shell!");
    }

    public void init() {
        System.out.println("my name is " + name);
    }
}
