package com.sly.jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * TODO: JDBC工具类
 *
 * @author leyuan
 * @date 2021/7/15 16:59
 */
public class JDBCUtils {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF8&useSSL=false";
        String userName = "root";
        String password = "123456";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url,userName, password);
    }
}
