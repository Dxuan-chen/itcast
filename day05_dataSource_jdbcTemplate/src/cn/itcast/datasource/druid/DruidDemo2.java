package cn.itcast.datasource.druid;

import cn.itcast.utils.JDBCUtils;

import javax.sql.rowset.JdbcRowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 */
public class DruidDemo2 {
    /**
    * 完成添加操作，给account表添加一条记录
    */

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            //1.获取连接
            conn = JDBCUtils.getConnection();
            //2.定义sql
            String sql = "insert into account values(null,?,?)";
            //3.获取pstmt对象
            pstmt = conn.prepareStatement(sql);
            //4.给？赋值
            pstmt.setString(1,"wangwu");
            pstmt.setDouble(2,3000);
            //5.执行sql
            int count = pstmt.executeUpdate();
            System.out.println(count);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6.释放资源
            JDBCUtils.close(pstmt,conn);
        }

    }
}
