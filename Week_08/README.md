# 第一题
## 题目
设计对前面的订单表数据进行水平分库分表，拆分 2 个库，每个库 16 张表。并在新结构在演示常见的增删改查操作。代码、sql 和配置文件，上传到 Github。

## 思路

订单表分库表，使用userId作为分库的参照，使用订单ID作为分表的依据。因为分库影响DataSource选择，分表影响操作的数据表。因此在DAO层，在执行操作时根据涉及到userId和orderId来选择合适的DataSource和table。如果没有都涉及则考虑广播。为了达到上述目标，设计BaseDAO接口，要求所有实际DAO都实现4个接口：

1. 根据userId返回DataSource`DataSource generateDatasource(Long userId);`
2. 根据orderId返回table `String generateTable(String orderId);`
3. 返回所有所有DataSource用于广播 `List<DataSource> getAllDatasource();`
4. 返回所有所有table用于广播 `List<String> getAllTables();`

本次作业，根据BaseDAO接口分别实现了单库表的`SingletonOrderDao`和2库16表的`ManyOrderDao`。 分别执行了增删改查操作，其中部分操作在多库表情况下需要广播。结果达到预期效果，数据分散到了指定的表中

## 步骤

1. 准备3个库，d0、d1、singleton，其中singleton用于模拟单库单order表。d0、d1分别有16张order表用于模拟2库16表。

2. d0、d1执行[sql](jdbc-shard/sql/d0.sql) ,创建16张表，singleton执行[sql](jdbc-shard/sql/singleton.sql)，创建1张order表

3. 创建spring项目，准备配置文件如下

   ```yaml
   hasSharding: true
   datasource:
     d0:
       jdbc-url: jdbc:mysql://localhost:3306/d0?useSSL=false
       username: root
       password: root
     d1:
       jdbc-url: jdbc:mysql://localhost:3306/d1?useSSL=false
       username: root
       password: root
     singleton:
       jdbc-url: jdbc:mysql://localhost:3306/singleton?useSSL=false
       username: root
       password: root
   ```

   

4. 创建[DataSourceConfig.java ](jdbc-shard/jdbc-shard/src/main/java/club/gaiaproject/homework/shard/config/DataSourceConfig.java) ，主要用于根据`hasSharding`配置，创建1个或多个`datasource`。

5. 创建[OrderConfiguration.java](jdbc-shard/jdbc-shard/src/main/java/club/gaiaproject/homework/shard/config/OrderConfiguration.java)，该配置文件主要用于根据`hasSharding`配置装配`OrderDao`如果为true增创建`ManyOrderDao`如果为false则创建`SingletonOrderDao`。

6. 编写抽象类[OrderDao.java](jdbc-shard/jdbc-shard/src/main/java/club/gaiaproject/homework/shard/dao/OrderDao.java)，该类实现了`BaseDao`接口，实现了实际增删改查逻辑，实现时会根据业务逻辑获取全部`datasource`或`table`做广播或拿到指定`datasource`或`table`，获取的方式是调用接口的相关方法。

7. 编写[SingletonOrderDao.java](jdbc-shard/jdbc-shard/src/main/java/club/gaiaproject/homework/shard/dao/SingletonOrderDao.java)，该类继承`orderDao`，主要工作是实现`BaseDao`的接口，由于是单表所有DataSource永远返回1个table也永远返回1个即可。

8. 编写[ManyOrderDao.java](jdbc-shard/jdbc-shard/src/main/java/club/gaiaproject/homework/shard/dao/ManyOrderDao.java)，该类继承`orderDao`，主要工作是实现`BaseDao`的接口，首先构建该类是需要将spring容器中所有的DataSource变为map<String,Datasoure>作为初始化参数。在选择DataSource时根据userId模2，选择table时根据orderId的hashcode模16。获取全部则返回全部。

9. 在[ShardApplication.java](jdbc-shard/jdbc-shard/src/main/java/club/gaiaproject/homework/shard/ShardApplication.java)，注入`OrderDao`，调用`OrderDao`中写的相关方法测试效果。结果显示`hasSharding`为false时全部数据都操作在`singleton`中，为true时，所有操作分散到了2个库的16张表上，与预期结果相符合。

# 第二题

## 题目 
基于 hmily TCC 或 ShardingSphere 的 Atomikos XA 实现一个简单的分布式事务应用 demo（二选一），提交到 Github。

此次选择实现hmily TCC实现demo







