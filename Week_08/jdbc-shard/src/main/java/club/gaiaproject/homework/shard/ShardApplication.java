package club.gaiaproject.homework.shard;

import club.gaiaproject.homework.shard.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Phoenix Luo
 * @version 2020/12/9
 **/
@SpringBootApplication
public class ShardApplication implements CommandLineRunner {
    @Autowired
    private OrderDao orderDao;
    
    public static void main(String[] args) {
        SpringApplication.run(ShardApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        // 增加 6 条数据
        orderDao.insert("1", 1L, "地址1", "电话1", 1.0F);
        orderDao.insert("2", 1L, "地址1", "电话1", 10.0F);
        orderDao.insert("3", 3L, "地址3", "电话3", 1.0F);
        orderDao.insert("4", 4L, "地址4", "电话4", -1.3F);
        
        orderDao.insert("5", 2L, "地址1", "电话1", 1.2F);
        orderDao.insert("6", 2L, "地址1", "电话1", 23.25F);
        orderDao.insert("7", 3L, "地址3", "电话3", -1.23F);
        orderDao.insert("8", 4L, "地址4", "电话4", 1.3F);
        
        // 搜索数据 如果分库表 搜索时就确定了库表
        orderDao.selectByUserAndOrderId(1L, "2");
        // 搜索数据 如果分库表 搜索数据会广播
        orderDao.selectByCreateTime(System.currentTimeMillis() - 10000L, System.currentTimeMillis());
        
        // 更新价格低于0元的账单状态为0 如果分库表 操作会被发送到指定数据的所有表中
        orderDao.updateByUserAndLessThanPrice(4L, 0.0F, 0);
        
        // 根据订单ID删除数据 如果分库表，则会广播到所有库的指定表中
        orderDao.deleteByOrderId("6");
    }
}
