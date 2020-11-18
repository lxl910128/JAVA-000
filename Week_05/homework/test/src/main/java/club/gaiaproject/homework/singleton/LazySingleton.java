package club.gaiaproject.homework.singleton;

/**
 * @author Phoenix Luo
 * @version 2020/11/18
 * <p>
 * 懒汉模式，线程不安全
 **/
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
