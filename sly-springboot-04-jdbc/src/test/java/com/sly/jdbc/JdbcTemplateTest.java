package com.sly.jdbc;

import com.sly.entities.Customer;
import com.sly.jdbc.utils.DruidUtils;
import com.sly.jdbc.utils.XMLUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * TODO: 测试JdbcTemplate对象的增删改查方法
 * @author leyuan
 * @date 2021/7/16 15:45
 */
public class JdbcTemplateTest {
    // Jdbc模板对象
    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());

    @Test
    public void insertTest() {
        // String sql = "insert into customer(cid,cust_name,cust_mobile,cust_address,cust_ticket) values(?,?,?,?,?)";
        String sql = XMLUtils.getSQLByKey("insertCustomer");
        Object[] data = {7,"小黑","19989323211","北京",10};
        int rows = jdbcTemplate.update(sql, data);
        System.out.println("insert rows = " + rows);
    }

    @Test
    public void updateTest() {
        // String sql = "update customer set cust_name=? where cid=?";
        String sql = XMLUtils.getSQLByKey("updateCustomer");
        Object[] data = {"老苏",7};
        int rows = jdbcTemplate.update(sql, data);
        System.out.println("update rows = " + rows);
    }

    @Test
    public void deleteTest() {
        // String sql = "delete from customer where cid=?";
        String sql = XMLUtils.getSQLByKey("deleteCustomer");
        Object[] data = {6};
        int rows = jdbcTemplate.update(sql, data);
        System.out.println("delete rows = " + rows);
    }

    @Test
    public void selectOneTest() {
        String sql = XMLUtils.getSQLByKey("selectCustomerById");
        Object[] data = {1};
        Customer customer = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), data);
        System.out.println(customer);
    }

    @Test
    public void selectCountTest() {
        String sql = XMLUtils.getSQLByKey("selectCustomerCount");
        Long customerCount = jdbcTemplate.queryForObject(sql, Long.class);
        System.out.println("总行数："+customerCount);
    }

    @Test
    public void batchDeleteAccountTest() {
        String sql = XMLUtils.getSQLByKey("batchDeleteCustomer");
        Integer[] data = {7,8,9};
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(data));
        int[] rows = jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1,list.get(i));
            }
            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
        System.out.println("受影响的行数："+rows.length);
    }
}
