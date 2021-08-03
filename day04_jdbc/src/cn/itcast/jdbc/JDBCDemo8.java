package cn.itcast.jdbc;

import cn.itcast.util.JDBCUtils;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 演示JDBC工具类
 *
 */
public class JDBCDemo8 {
    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
//            //1. 注册驱动
//            Class.forName("com.mysql.jdbc.Driver");

//            //2. 获取Connection对象
//            conn = (Connection) DriverManager.getConnection("jdbc:mysql:///db","root","root");
            conn = (Connection) JDBCUtils.getConnection();
            //3. 定义sql
            String sql = "select * from account";
            //4. 获取执行sql的对象Statement
            stmt = conn.createStatement();
            //5. 执行sql
            rs = stmt.executeQuery(sql);
            //6. 处理结果
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString("name");
                double balance = rs.getDouble(3);
                System.out.println(id+"----"+name+"----"+balance);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //stmt.close();
            //7. 释放资源

//            if(rs!=null){
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(stmt!=null){
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            if(conn!=null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
            JDBCUtils.close(rs,stmt,conn);

        }

    }
}
