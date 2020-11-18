package club.gaiaproject.homework.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Phoenix Luo
 * @version 2020/11/18
 **/
public class TestJDBC {
    private static String URL = "jdbc:mysql://lc0:3306/test?useUnicode=true&characterEncoding=utf-8";
    private static String Driver = "com.mysql.jdbc.Driver";
    private static String user = "test";
    private static String password = "test";

    public static void main(String[] args) throws SQLException {
        TestJDBC testJDBC = new TestJDBC();
        Connection conn = null;
        try {
            Class.forName(Driver);
            conn = DriverManager.getConnection(URL, user, password);
            testJDBC.select(conn);
            testJDBC.insert(conn);
            testJDBC.delete(conn);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        // 事务
        try {
            conn = DriverManager.getConnection(URL, user, password);
            // 设置隔离级别
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            // 关闭自动提交:
            conn.setAutoCommit(false);

            testJDBC.select(conn);
            testJDBC.insert(conn);
            testJDBC.delete(conn);

            // 提交事务:
            conn.commit();
        } catch (SQLException sqlE) {
            // 回滚
            conn.rollback();
        } finally {
            // 重新打开自动提交
            conn.setAutoCommit(true);
            conn.close();
        }

        // 连接池
        HikariConfig config = new HikariConfig("/hikari.properties");
        try (HikariDataSource ds = new HikariDataSource(config)) {
            conn = ds.getConnection();
            testJDBC.select(conn);
            testJDBC.insert(conn);
            testJDBC.delete(conn);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void select(Connection conn) throws SQLException {
        // 查询
        String querySql = "select * from account limit 10";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(querySql);
        while (rs.next()) {
            System.out.println(String.format("id: %d ,username %s", rs.getLong(1), rs.getString("username")));
        }
        rs.close();
    }

    private void delete(Connection conn) throws SQLException {
        // 删除
        String deleteSql = "delete from account where username ='test'";
        PreparedStatement delPstmt = conn.prepareStatement(deleteSql);
        int i = delPstmt.executeUpdate();
        System.out.println("resutl: " + i);
        delPstmt.close();
    }

    private void insert(Connection conn) throws SQLException {
        // 插入
        String insertSql = "insert into account (group_id,username,department) values(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(insertSql);
        pstmt.setLong(1, 2233L);
        pstmt.setString(2, System.currentTimeMillis() + "L");
        pstmt.setString(3, "department");
        System.out.println(pstmt.executeUpdate());
        pstmt.close();

    }
}
