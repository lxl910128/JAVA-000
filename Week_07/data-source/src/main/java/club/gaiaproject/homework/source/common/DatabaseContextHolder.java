package club.gaiaproject.homework.source.common;

import java.util.Optional;

/**
 * @author Phoenix Luo
 * @version 2020/11/30
 **/
public class DatabaseContextHolder {
    private static ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    
    public static void setDbType(String dbType) {
        contextHolder.set(dbType);
    }
    
    public static Optional<String> getDbType() {
        return Optional.ofNullable(contextHolder.get());
    }
    
    public static void clearDbType() {
        contextHolder.remove();
    }
}
