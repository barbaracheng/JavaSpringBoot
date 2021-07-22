package com.sly.mapper.impl;

import cn.hutool.core.util.StrUtil;
import com.sly.entities.Customer;
import com.sly.jdbc.utils.DruidUtils;
import com.sly.jdbc.utils.XMLUtils;
import com.sly.mapper.CustomerMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/17 10:49
 */
public class CustomerMapperImpl implements CustomerMapper {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidUtils.getDataSource());

    /**
     * 根据用户名称获取用户信息
     *
     * @param custName
     * @return
     */
    @Override
    public List<Customer> searchCustomer(String custName) {
        String sql = XMLUtils.getSQLByKey("listCustomer");
        StringBuilder sb = new StringBuilder(sql);
        sb.append(" where cust_name like concat('%',?,'%')");
        return jdbcTemplate.query(sb.toString(),new BeanPropertyRowMapper<>(Customer.class),custName);
    }

    /**
     * 显示所有用户信息
     *
     * @return Customer实体列表
     */
    @Override
    public List<Customer> listCustomer() {
        String sql = XMLUtils.getSQLByKey("listCustomer");
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(Customer.class));
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param cid 用户id
     * @return 用户信息
     */
    @Override
    public Customer getCustomerById(Integer cid) {
        String sql = XMLUtils.getSQLByKey("getCustomerById");
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class), cid);
    }

    /**
     * 添加客户信息
     * 约定优于配置优于编码：save or  insert + 实体类名称
     *
     * @param customer 要添加的客户信息
     * @return 受影响行数。大于0：添加成功。否则：添加失败
     */
    @Override
    public int saveCustomer(Customer customer) {
        String sql = XMLUtils.getSQLByKey("saveCustomer");
        return jdbcTemplate.update(sql,customer.getCustName(),customer.getCustMobile(),customer.getCustAddress(),customer.getCustTicket());
    }

    /**
     * 根据客户编号删除客户信息
     *
     * @param cid 客户编号
     * @return 受影响行数。大于0：删除成功。否则：删除失败
     */
    @Override
    public int deleteCustomerById(Integer cid) {
        String sql = XMLUtils.getSQLByKey("deleteCustomer");
        return jdbcTemplate.update(sql,cid);
    }

    /**
     * 更新客户信息
     * 约定优于配置优于编码：update+实体类名称
     *
     * @param customer 要修改的客户信息
     * @return 受影响行数。大于0：修改成功。否则：修改失败
     */
    @Override
    public int updateCustomer(Customer customer) {
        String sql = XMLUtils.getSQLByKey("updateCustomer");
        return jdbcTemplate.update(sql,customer.getCustName(),customer.getCustMobile(),customer.getCustAddress(),customer.getCustTicket(),customer.getCid());
    }
}
