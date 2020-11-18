package club.gaiaproject.homework.proxy;

/**
 * @author Phoenix Luo
 * @version 2020/11/17
 **/
public class PersonStaticProxy implements Person {
    @Override
    public void say(String sth) {
        System.out.println("StaticProxy: hello word!  " + sth);
    }
}
