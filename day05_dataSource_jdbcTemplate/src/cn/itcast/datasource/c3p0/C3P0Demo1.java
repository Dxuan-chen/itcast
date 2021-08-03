package cn.itcast.datasource.c3p0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
public class C3P0Demo1 {
    public static void main(String[] args) throws SQLException {
//        //获取DataSource，使用默认配置
//        DataSource ds = new ComboPooledDataSource();

        //获取DataSource，使用指定名称配置
        DataSource ds = new ComboPooledDataSource("otherc3p0");

        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
