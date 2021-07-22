package com.sly.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * TODO:获取表的所有行
 *
 * @author leyuan
 * @date 2021/7/15 16:26
 */
public class JDBCTest1 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF8&useSSL=false";
        String userName = "root";
        String password = "123456";

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String sql = "select cid,cust_name,cust_mobile,cust_address,cust_ticket from customer";

        try(
                Connection conn = DriverManager.getConnection(url,userName, password);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ){
            // 遍历结果集的数据
            // rs.next()游标指针下移，如果有数据，读取数据。没有数据退出循环
            while (rs.next()) {
                // rs.getInt(1) 读取结果集第一列数据，该列是int类型所以调用getInt方法
                int cid = rs.getInt(1);
                String custName = rs.getString(2);
                String custMobile = rs.getString(3);
                String custAddress = rs.getString(4);
                int custTicket = rs.getInt(5);
                System.out.println(cid+"--"+custName+"--"+custMobile+"--"+custAddress+"--"+custTicket);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

}
