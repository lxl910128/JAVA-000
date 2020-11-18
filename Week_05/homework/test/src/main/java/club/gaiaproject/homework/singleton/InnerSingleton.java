package club.gaiaproject.homework.singleton;

/**
 * @author Phoenix Luo
 * @version 2020/11/18
 * <p>
 * 内部类 lazy 线程
 **/
public class InnerSingleton {

    public static String STATIC = "static";

    public static final String FINAL = "final";


    // 禁止实例化
    private InnerSingleton() {
        System.out.println("初始化 InnerSingleton");
    }

    // 调用 时才加载SingletonHolder类
    public static final InnerSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // 外部类加载/初始化时并不需要立即加载内部类
    // 内部类不被加载则不去初始化INSTANCE，故而不占内存。
    private static class SingletonHolder {
        private static final InnerSingleton INSTANCE = new InnerSingleton();
    }




    /*

    生命周期：加载-》验证-》准备-》解析-》初始化-》使用-》卸载
    Java类加载会初始化的情况有且仅有以下五种：（也称为主动引用）

    1.遇到new（用new实例对象），getStatic（读取一个静态字段），putstatic（设置一个静态字段），invokeStatic（调用一个类的静态方法）这四条指令字节码命令时

    2.使用Java.lang.reflect包的方法对类进行反射调用时，如果此时类没有进行init，会先init。

    3.当初始化一个类时，如果其父类没有进行初始化，先初始化父类

    4.jvm启动时，用户需要指定一个执行的主类（包含main的类）虚拟机会先执行这个类

    5.当使用JDK1.7的动态语言支持的时候，当java.lang.invoke.MethodHandler实例后的结果是REF-getStatic/REF_putstatic/REF_invokeStatic的句柄，并且这些句柄对应的类没初始化的话应该首先初始。

    注意：除以上5种方法外，所有引用类的方法都不会触发初始化，称为被动引用。

    被动引用的例子：
    1.通过子类来引用父类的静态字段，只会触发父类的初始化，不会触发子类的初始化。
    2.superclass () sc = new superclass[];//不会触发superclass初始化，因为底层实现是直接生成object子类。
    3.引用一个类的 静态 常量也不会触发初始化，因为常量在编译阶段已经确认。

    */
}
