package com.sly.jdbc;

import com.sly.jdbc.utils.JDBCUtils2;
import com.sly.jdbc.utils.XMLUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/16 11:03
 */
public class XMLUtilsTest {
    @Test
    public void XMLUtilsTest() throws SQLException, ClassNotFoundException {

        Connection conn = JDBCUtils2.getConnection();
        //使用XML配置文件的静态方法获取sql语句
        String sql = XMLUtils.getSQLByKey("listCustomer");
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int cid = rs.getInt(1);
            String custName = rs.getString(2);
            String custMobile = rs.getString(3);
            String custAddress = rs.getString(4);
            int custTicket = rs.getInt(5);
            System.out.println(cid+"--"+custName+"--"+custMobile+"--"+custAddress+"--"+custTicket);
        }
    }
}
