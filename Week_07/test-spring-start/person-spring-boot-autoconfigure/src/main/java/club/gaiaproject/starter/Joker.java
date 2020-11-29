package club.gaiaproject.starter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Phoenix Luo
 * @version 2020/11/18
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Joker {
    private String name;

    public void say() {
        System.out.println(name + " say: I'm Joker! ");
    }
}
