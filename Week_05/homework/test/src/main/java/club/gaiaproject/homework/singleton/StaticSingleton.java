package club.gaiaproject.homework.singleton;

/**
 * @author Phoenix Luo
 * @version 2020/11/18
 * <p>
 * 饿汉模式
 **/
public class StaticSingleton {
    private static StaticSingleton instance = new StaticSingleton();

    private StaticSingleton() {
    }

    public static StaticSingleton getInstance() {
        return instance;
    }
}
