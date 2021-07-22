package com.sly.jdbc;

import com.sly.jdbc.utils.DruidUtils;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * TODO: 测试Druid工具方法
 *
 * @author leyuan
 * @date 2021/7/16 14:52
 */
public class DruidPoolTest {
    /**
     * 测试getDataSource()方法
     * 返回结果为：
     * CreateTime:"2021-07-16 14:54:25",
     * 	ActiveCount:0,
     * 	PoolingCount:0,
     * 	CreateCount:0,
     * 	DestroyCount:0,
     * 	CloseCount:0,
     * 	ConnectCount:0,
     * 	Connections:[
     * 	]
     * 	小结：在
     */
    @Test
    public void getDataSourceTest() {
        DataSource ds = DruidUtils.getDataSource();
        System.out.println(ds);
    }

    @Test
    public void getConnectionTest() throws SQLException {
        DataSource ds = DruidUtils.getDataSource();
        System.out.println(ds);

        Connection conn = ds.getConnection();
        System.out.println(ds);
    }

    /**
     * 场景：使用Druid连接池的工具方法获取Connection对象，然后打印customer表的所有内容
     *
     */
    @Test
    public void DruidPoolTest() {
        String sql = "select cid,cust_name,cust_mobile,cust_address,cust_ticket from customer";
        try (
                Connection conn = DruidUtils.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ){
            while (rs.next()) {
                int cid = rs.getInt(1);
                String custName = rs.getString(2);
                String custMobile = rs.getString(3);
                String custAddress = rs.getString(4);
                int custTicket = rs.getInt(5);
                System.out.println(cid+"---"+custName+"---"+custMobile+"---"+custAddress+"---"+custTicket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
