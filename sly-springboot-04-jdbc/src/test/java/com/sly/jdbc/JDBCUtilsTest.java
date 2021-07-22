package com.sly.jdbc;

import com.sly.jdbc.utils.JDBCUtils;
import com.sly.jdbc.utils.JDBCUtils2;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * TODO:测试JDBC连接的工具方法
 *
 * @author leyuan
 * @date 2021/7/15 17:33
 */
public class JDBCUtilsTest {
    @Test
    public void JDBCTest () throws SQLException, ClassNotFoundException {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn);

        Connection conn1 = JDBCUtils2.getConnection();
        System.out.println(conn1);
    }
}
