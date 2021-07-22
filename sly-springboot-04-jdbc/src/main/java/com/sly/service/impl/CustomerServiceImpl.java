package com.sly.service.impl;

import com.sly.entities.Customer;
import com.sly.mapper.CustomerMapper;
import com.sly.mapper.impl.CustomerMapperImpl;
import com.sly.service.CustomerService;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/17 10:59
 */
public class CustomerServiceImpl implements CustomerService {
    // CustomerMapper对象
    private CustomerMapper customerMapper = new CustomerMapperImpl();

    /**
     * 根据用户名称获取用户信息
     *
     * @param custName
     * @return
     */
    @Override
    public List<Customer> searchCustomer(String custName) {
        return customerMapper.searchCustomer(custName);
    }

    /**
     * 显示所有用户信息
     *
     * @return Customer实体列表
     */
    @Override
    public List<Customer> listCustomer() {
        return customerMapper.listCustomer();
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param cid 用户id
     * @return 用户信息
     */
    @Override
    public Customer getCustomerById(Integer cid) {
        return customerMapper.getCustomerById(cid);
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
        return customerMapper.saveCustomer(customer);
    }

    /**
     * 根据客户编号删除客户信息
     *
     * @param cid 客户编号
     * @return 受影响行数。大于0：删除成功。否则：删除失败
     */
    @Override
    public int deleteCustomerById(Integer cid) {
        return customerMapper.deleteCustomerById(cid);
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
        return customerMapper.updateCustomer(customer);
    }
}
