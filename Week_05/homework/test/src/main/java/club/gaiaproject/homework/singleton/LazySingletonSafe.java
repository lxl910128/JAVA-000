package club.gaiaproject.homework.singleton;

/**
 * @author Phoenix Luo
 * @version 2020/11/18
 * <p>
 * 懒汉，安全
 **/
public class LazySingletonSafe {
    private static LazySingletonSafe instance;

    private LazySingletonSafe() {
    }

    public static synchronized LazySingletonSafe getInstance() {
        if (instance == null) {
            instance = new LazySingletonSafe();
        }
        return instance;
    }
}
