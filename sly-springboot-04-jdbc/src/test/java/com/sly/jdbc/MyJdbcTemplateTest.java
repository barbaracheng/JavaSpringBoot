package com.sly.jdbc;

import com.sly.entities.Customer;
import com.sly.jdbc.utils.JdbcTemplate;
import com.sly.jdbc.utils.XMLUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * TODO:测试JDBC+反射创建的JdbcTemplate类
 *
 * @author leyuan
 * @date 2021/7/17 13:20
 */
public class MyJdbcTemplateTest {
    private JdbcTemplate<Customer> jdbcTemplate;
    @Before
    public void init() {
        jdbcTemplate = new JdbcTemplate<>(Customer.class);
        System.out.println("Before.............");
    }

    @After
    public void destroy() {
        jdbcTemplate = null;
        System.out.println("After.............");
    }


    @Test
    public void updateTest() throws Exception {
        String sql = XMLUtils.getSQLByKey("updateCustomer");
        Object[] data = {"李丽","12345632345","上海",90, 3};
        int rows = jdbcTemplate.update(sql,data);
        System.out.println("受影响的行数：" + rows);
    }

    @Test
    public void queryForObjectTest() throws Exception {
        String sql = XMLUtils.getSQLByKey("getCustomerById");
        Object[] data = {3};
        Customer customer = jdbcTemplate.queryForObject(sql, data);
        System.out.println("客户信息："+customer);
    }

    @Test
    public void queryTest() throws Exception {
        String sql = XMLUtils.getSQLByKey("listCustomer");
        List<Customer> customerList = jdbcTemplate.query(sql);
        for (Object customer : customerList) {
            System.out.println(customer);
        }

    }
}
