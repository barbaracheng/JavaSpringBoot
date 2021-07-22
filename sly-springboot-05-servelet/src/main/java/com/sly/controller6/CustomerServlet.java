package com.sly.controller6;

import com.sly.entities.Customer;
import com.sly.service.CustomerService;
import com.sly.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/19 16:19
 */
public class CustomerServlet extends HttpServlet {
   private CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取web.xml配置文件的全局配置信息
//        String contentType = getServletConfig()
//                .getServletContext()
//                .getInitParameter("contentType");
//        System.out.println("contentType = "+contentType);

//        resp.setContentType(contentType);

        List<Customer> list = customerService.listCustomer();
        // 获取响应对象的输出管道，将客户列表信息响应给浏览器
        resp.getWriter().print(list);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
