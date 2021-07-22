package com.sly.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * TODO:测试数据库连接
 *PreparedStatement用于执行SQL语句，该对象必须在Connection对象创建之后创建
 *如果SQL语句的是未知的，那么在执行之前必须先设置占位符的值
 * executeUpdate()方法专门执行DML语句，返回类型是int类型，表示受影响行数
 * @author leyuan
 * @date 2021/7/15 15:11
 */


public class JDBCTest {
    public static void main(String[] args) {
        //
        String url = "jdbc:mysql://localhost:3306/dbem?useUnicode=true&characterEncoding=UTF8&useSSL=false";
        String userName = "root";
        String password = "123456";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner input = new Scanner(System.in);
        System.out.println("请输入员工编号：");
        String id = input.next();
        // ？在JDBC中表示占位符，他的值是未知的，只有在程序运行期间才能够决定
        String sql = "UPDATE salary set income = 10000 WHERE employeeID = ?";
        try (
                Connection conn = DriverManager.getConnection(url, userName, password);

                PreparedStatement ps = conn.prepareStatement(sql);
                ) {
            System.out.println(conn);
            // 将输入的id值填充到SQL语句的占位符？中
            // 参数1：表示占位符？的下标，此时1表示SQL语句中的第一个？
            // 参数2：表示占位符实际的值，此时将id填充到SQL语句的第一个占位符
            ps.setString(1,id);
            // 执行SQl语句
            // ps对象专门用于执行SQl语句
            // 如果SQL语句是DML调用executeUpdate()方法执行
            // 如果SQL语句是DQL调用executeQuery()方法执行
            // rows 表示执行DML返回受影响行数，大于0执行DML成功，否则执行失败
            int rows = ps.executeUpdate();
            System.out.println("rows = " + rows);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
