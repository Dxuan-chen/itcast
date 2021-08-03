package cn.itcast.jdbc;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 功能描述：
 *
 * @author 陈铉锋
 * @date 2021/7/2
 */
public class JDBCDemo2 {
    public static void main(String[] args){
        Connection conn = null;
        Statement stmt = null;
        try {
            //1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2. 定义sql
            String sql = "insert into account values(null,'王五',3000)";
            //3. 获取Connection对象
            conn = (Connection) DriverManager.getConnection("jdbc:mysql:///db","root","root");
            //4. 获取执行sql的对象Statement
            stmt = conn.createStatement();
            //5. 执行sql
            int count = stmt.executeUpdate(sql);//影响的行数
            //6. 处理结果
            System.out.println(count);
            if(count>0){
                System.out.println("添加成功");
            }else{
                System.out.println("添加失败");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //stmt.close();
            //7. 释放资源
            //为了避免空指针异常
            if(stmt!=null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}

