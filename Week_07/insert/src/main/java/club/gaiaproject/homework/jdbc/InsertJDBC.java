package club.gaiaproject.homework.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * @author Phoenix Luo
 * @version 2020/11/18
 **/
public class InsertJDBC {
    private static String URL = "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=utf-8&useServerPrepStmts=true&cachePrepStmts=true";
    private static String Driver = "com.mysql.jdbc.Driver";
    private static String user = "root";
    private static String password = "root";
    
    public static void main(String[] args) throws SQLException {
        InsertJDBC testJDBC = new InsertJDBC();
        Connection conn = null;
        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(URL, user, password);
            // 第一种方式
            System.out.println("总计消耗" + testJDBC.insertByStream(conn) + "ms");
            // 第二种方式
            // System.out.println("总计消耗" + testJDBC.insertManyByStream(conn) + "ms");
            // 第三种方式
            // System.out.println("总计消耗" + testJDBC.insertByPrepareStatement(conn) + "ms");
            // 第四种方式
            // System.out.println("总计消耗" + testJDBC.insertManyByPrepareStatement(conn) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
    
    
    /**
     * @param conn
     * @throws SQLException
     */
    private Long insertByStream(Connection conn) throws SQLException {
        Long timeStamp = System.currentTimeMillis();
        Long branchTime = timeStamp;
        //String insertSqlFormat = "insert into goods ('name','keyword','type_id','stock','store_id','price','create_time','update_time') values ('','','')";
        String insertSqlFormat = "insert into store (`name`,`store_account`,`keyword`,`create_time`,`update_time`) values ('%s','%s','%s',%d,%d)";
        for (int i = 0; i < 1000000; i++) {
            String[] uuids = UUID.randomUUID().toString().split("-");
            conn.createStatement().execute(String.format(insertSqlFormat, uuids[0], uuids[1], uuids[2], timeStamp, timeStamp));
            if (i % 10000 == 0) {
                System.out.println("已插入" + i + "条  sql 耗时：" + (System.currentTimeMillis() - timeStamp) + "本批次耗时" + (System.currentTimeMillis() - branchTime));
                branchTime = System.currentTimeMillis();
            }
        }
        return System.currentTimeMillis() - timeStamp;
    }
    
    /**
     * @param conn
     * @throws SQLException
     */
    private Long insertManyByPrepareStatement(Connection conn) throws SQLException {
        Long timeStamp = System.currentTimeMillis();
        Long branchTime = timeStamp;
        //String insertSqlFormat = "insert into goods ('name','keyword','type_id','stock','store_id','price','create_time','update_time') values ('','','')";
        String insertSqlFormat = "insert into store (`name`,`store_account`,`keyword`,`create_time`,`update_time`) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertSqlFormat);
        for (int i = 0; i < 1000000; i++) {
            String[] uuids = UUID.randomUUID().toString().split("-");
            preparedStatement.setString(1, uuids[0]);
            preparedStatement.setString(2, uuids[1]);
            preparedStatement.setString(3, uuids[2]);
            preparedStatement.setLong(4, timeStamp);
            preparedStatement.setLong(5, timeStamp);
            preparedStatement.addBatch();
            //1000次提交一次
            if (i % 10 == 0) {
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }
            
            if (i % 10000 == 0) {
                System.out.println("已插入" + i + "条  sql 耗时：" + (System.currentTimeMillis() - timeStamp) + "本批次耗时" + (System.currentTimeMillis() - branchTime));
                branchTime = System.currentTimeMillis();
            }
            
        }
        preparedStatement.executeBatch();
        preparedStatement.clearBatch();
        return System.currentTimeMillis() - timeStamp;
    }
    
    private Long insertByPrepareStatement(Connection conn) throws SQLException {
        Long timeStamp = System.currentTimeMillis();
        Long branchTime = timeStamp;
        //String insertSqlFormat = "insert into goods ('name','keyword','type_id','stock','store_id','price','create_time','update_time') values ('','','')";
        String insertSqlFormat = "insert into store (`name`,`store_account`,`keyword`,`create_time`,`update_time`) values (?,?,?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(insertSqlFormat);
        for (int i = 0; i < 1000000; i++) {
            String[] uuids = UUID.randomUUID().toString().split("-");
            preparedStatement.setString(1, uuids[0]);
            preparedStatement.setString(2, uuids[1]);
            preparedStatement.setString(3, uuids[2]);
            preparedStatement.setLong(4, timeStamp);
            preparedStatement.setLong(5, timeStamp);
            preparedStatement.executeUpdate();
            if (i % 10000 == 0) {
                System.out.println("已插入" + i + "条  sql 耗时：" + (System.currentTimeMillis() - timeStamp) + "本批次耗时" + (System.currentTimeMillis() - branchTime));
                branchTime = System.currentTimeMillis();
            }
        }
        return System.currentTimeMillis() - timeStamp;
    }
    
    /**
     * @param conn
     * @throws SQLException
     */
    private Long insertManyByStream(Connection conn) throws SQLException {
        Long timeStamp = System.currentTimeMillis();
        Long branchTime = timeStamp;
        //String insertSqlFormat = "insert into goods ('name','keyword','type_id','stock','store_id','price','create_time','update_time') values ('','','')";
        String insertSqlFormat = "insert into store (`name`,`store_account`,`keyword`,`create_time`,`update_time`) values " +
                "('%s','%s','%s',%d,%d),('%s','%s','%s',%d,%d),('%s','%s','%s',%d,%d),('%s','%s','%s',%d,%d),('%s','%s','%s',%d,%d)," +
                "('%s','%s','%s',%d,%d),('%s','%s','%s',%d,%d),('%s','%s','%s',%d,%d),('%s','%s','%s',%d,%d),('%s','%s','%s',%d,%d)";
        for (int i = 0; i < 100000; i++) {
            
            String[][] uuids = new String[10][3];
            for (int j = 0; j < 10; j++) {
                String[] uuid = UUID.randomUUID().toString().split("-");
                uuids[j][0] = uuid[0];
                uuids[j][1] = uuid[1];
                uuids[j][2] = uuid[2];
            }
            // String[] uuids = UUID.randomUUID().toString().split("-");
            conn.createStatement().execute(String.format(insertSqlFormat,
                    uuids[0][0], uuids[0][1], uuids[0][2], timeStamp, timeStamp,
                    uuids[1][0], uuids[1][1], uuids[1][2], timeStamp, timeStamp,
                    uuids[2][0], uuids[2][1], uuids[2][2], timeStamp, timeStamp,
                    uuids[3][0], uuids[3][1], uuids[3][2], timeStamp, timeStamp,
                    uuids[4][0], uuids[4][1], uuids[4][2], timeStamp, timeStamp,
                    uuids[5][0], uuids[5][1], uuids[5][2], timeStamp, timeStamp,
                    uuids[6][0], uuids[6][1], uuids[6][2], timeStamp, timeStamp,
                    uuids[7][0], uuids[7][1], uuids[7][2], timeStamp, timeStamp,
                    uuids[8][0], uuids[8][1], uuids[8][2], timeStamp, timeStamp,
                    uuids[9][0], uuids[9][1], uuids[9][2], timeStamp, timeStamp));
            if (i % 1000 == 0) {
                System.out.println("已插入" + i + "条  sql 耗时：" + (System.currentTimeMillis() - timeStamp) + "本批次耗时" + (System.currentTimeMillis() - branchTime));
                branchTime = System.currentTimeMillis();
            }
        }
        return System.currentTimeMillis() - timeStamp;
    }
    
}
