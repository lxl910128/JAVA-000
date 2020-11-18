package club.gaiaproject.homework.singleton;

/**
 * @author Phoenix Luo
 * @version 2020/11/18
 *
 * 双检验 锁 懒汉 线程安全
 **/
public class LockSingleton {
    // 保持可见
    private volatile static LockSingleton singleton;

    private LockSingleton() {
    }

    public static LockSingleton getSingleton() {
        if (singleton == null) {
            // 锁
            synchronized (LockSingleton.class) {
                if (singleton == null) {
                    singleton = new LockSingleton();
                }
            }
        }
        return singleton;
    }
}
