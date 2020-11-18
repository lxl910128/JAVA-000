package club.gaiaproject.homework.singleton;

/**
 * @author Phoenix Luo
 * @version 2020/11/18
 **/
public class Main {
    public static void main(String[] args) {
        System.out.println(InnerSingleton.FINAL);
        System.out.println("没加载 InnerSingleton、SingletonHolder");
        System.out.println(InnerSingleton.STATIC);
        System.out.println("加载 InnerSingleton、没加载 SingletonHolder");
        System.out.println(InnerSingleton.getInstance());
    }
}
