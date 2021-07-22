package com.sly.controller9;

import com.sly.common.BaseServlet;
import com.sly.entities.Customer;
import com.sly.service.CustomerService;
import com.sly.service.impl.CustomerServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/20 10:26
 */
@WebServlet(urlPatterns = "/cust3",loadOnStartup = 3)
public class CustomerServlet extends BaseServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    public void listCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        pw.print(customerService.listCustomer());
    }
    public void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer cid = Integer.parseInt(req.getParameter("cid"));
        int rows = customerService.deleteCustomerById(cid);
        PrintWriter pw = resp.getWriter();
        if (rows > 0) {
            pw.print("删除成功");
        } else {
            pw.print("删除失败");
        }

    }

    public void selectOne(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer cid = Integer.parseInt(req.getParameter("cid"));
        Customer customer = customerService.getCustomerById(cid);
        PrintWriter writer = resp.getWriter();
        writer.print(customer);
    }
}
