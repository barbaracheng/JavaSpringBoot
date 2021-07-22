package com.sly.mapper;

import com.sly.entities.Customer;
import com.sly.jdbc.utils.XMLUtils;
import com.sly.mapper.impl.CustomerMapperImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * TODO:测试CustomerMapperImpl类的方法
 * Mapper层
 * @author leyuan
 * @date 2021/7/17 15:31
 */
public class CustomerMapperTest {
    private CustomerMapper customerMapper;
    @Before
    public void init() {
        // CustomerMapper对象
        customerMapper= new CustomerMapperImpl();
    }
    @After
    public void destroy() {
        customerMapper = null;
    }

    @Test
    public void listCustomerTest() {
        List<Customer> customerList = customerMapper.listCustomer();
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }

    @Test
    public void getCustomerByIdTest() {
        Customer customer = customerMapper.getCustomerById(11);
        System.out.println(customer);
    }

    @Test
    public void saveCustomerTest() {
        Customer customer = new Customer(-1,"小刘","17849302123","北京",80);
        int rows = customerMapper.saveCustomer(customer);
        System.out.println("save rows = "+rows);
    }

    @Test
    public void searchCustomerTest() {
        List<Customer> customerList = customerMapper.searchCustomer("老");
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
    }
    @Test
    public void updateCustomerTest() {
        Customer customer = new Customer(6,"小张","17849302123","北京",80);
        int rows = customerMapper.updateCustomer(customer);
        System.out.println(rows);

    }

}
