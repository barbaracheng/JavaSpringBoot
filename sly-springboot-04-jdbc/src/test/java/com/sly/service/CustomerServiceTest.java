package com.sly.service;

import com.sly.entities.Customer;
import com.sly.service.impl.CustomerServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO:测试CustomerService类的方法
 * 业务逻辑层
 * @author leyuan
 * @date 2021/7/17 11:02
 */
public class CustomerServiceTest {
    CustomerService customerService;

    @Before
    public void init() {
        customerService = new CustomerServiceImpl();
    }

    @After
    public void destory() {
        customerService = null;
    }

    @Test
    public void listCustomerTest() {
        System.out.println(customerService.listCustomer());
    }

    @Test
    public void getCustomerByIdTest() {
        System.out.println(customerService.getCustomerById(11));
    }

    @Test
    public void saveCustomerTest() {
        Customer customer = new Customer(11,"小张","17849302123","北京",80);
        System.out.println(customerService.saveCustomer(customer));
    }

    @Test
    public void deleteCustomerByIdTest() {
        System.out.println(customerService.deleteCustomerById(5));
    }

    @Test
    public void updateCustomerTest() {
        Customer customer = new Customer(3,"丽丽","12345632345","上海",90);
        System.out.println(customerService.updateCustomer(customer));
    }
}
