package com.sly.controller8;

import com.sly.entities.Customer;
import com.sly.service.CustomerService;
import com.sly.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO: CustomerServlet使用了注解，继承了HttpServlet，覆写了父类的doGet和doPost方法
 *
 * @author leyuan
 * @date 2021/7/20 10:13
 */
@WebServlet(urlPatterns = "/cust2",loadOnStartup = 2)
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methond = req.getParameter("method");
        // 获取method的值并与下面的值进行比较，但是一旦又有新的需求，又得在这里改动代码，违反了对扩展开发，对修改关闭的原则
        switch (methond) {
            case "listCustomer":
                listCustomer(req,resp);
                break;
            case "selectOne":
                selectOne(req,resp);
                break;
            case "deleteCustomer":
                deleteCustomer(req,resp);
                break;
            default:
                listCustomer(req,resp);
                break;
        }
    }

    private void listCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        pw.print(customerService.listCustomer());
    }
    private void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer cid = Integer.parseInt(req.getParameter("cid"));
        int rows = customerService.deleteCustomerById(cid);
        PrintWriter pw = resp.getWriter();
        if (rows > 0) {
            pw.print("删除成功");
        } else {
            pw.print("删除失败");
        }

    }

    private void selectOne(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Integer cid = Integer.parseInt(req.getParameter("cid"));
        Customer customer = customerService.getCustomerById(cid);
        PrintWriter writer = resp.getWriter();
        writer.print(customer);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
