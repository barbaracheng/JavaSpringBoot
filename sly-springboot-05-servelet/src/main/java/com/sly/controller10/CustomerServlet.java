package com.sly.controller10;

import com.sly.common.BaseServlet;
import com.sly.entities.Customer;
import com.sly.service.CustomerService;
import com.sly.service.impl.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/20 16:35
 */
@WebServlet(urlPatterns = "/cust4")
@Slf4j
public class CustomerServlet extends BaseServlet {
   private CustomerService customerService = new CustomerServiceImpl();

   // 哈希集合存储水票的种类
   private static final Set<Integer> TICKET_SET = new LinkedHashSet<>();

   static {
        TICKET_SET.add(10);
        TICKET_SET.add(20);
        TICKET_SET.add(30);
        TICKET_SET.add(50);
        TICKET_SET.add(100);
        TICKET_SET.add(200);
        TICKET_SET.add(500);
   }

    /**
     * 将客户列表返回给前端页面
     * @param req
     * @param resp
     * @throws IOException
     */
    public void listCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Customer> list = customerService.listCustomer();
        // 打印日志之前要判断日志的级别
        // 打印日志之前没有加判断会对程序性能造成影响
        // 假设此时日志级别是INFO，DEBUG是无效的但是还是执行了
//        log.debug("客户信息：",list);
        if (log.isDebugEnabled()) {
            log.debug("所有客户信息："+list.toString());
        }

        // 后端控制器的数据共享到前端页面
        req.setAttribute("custList",list);
        // 跳转到前端页面
        req.getRequestDispatcher("/WEB-INF/pages/cust_list.jsp").forward(req,resp);
    }

    /**
     * 根据客户姓名查询对应客户信息
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void searchCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String custName = req.getParameter("custName");

        List<Customer> list = customerService.searchCustomer(custName);
        if (log.isDebugEnabled()) {
            log.debug("查询到的客户信息："+list.toString());
        }

        req.setAttribute("custList",list);
        req.getRequestDispatcher("/WEB-INF/pages/cust_list.jsp").forward(req,resp);
    }

    /**
     * 预处理添加用户的操作，点击添加客户按钮即跳转到cust_save.jsp页面
     * @param req
     * @param resp
     * @throws IOException
     * @throws ServletException
     */
    public void preSaveCustomer(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getRequestDispatcher("/WEB-INF/pages/cust_save.jsp").forward(req,resp);
    }

    /**
     * 客户提交表单，添加客户信息
     * 步骤：
     * 1 取 ：表单参数
     * 2 掉 ： CustomerService的添加方法
     * 3 转： 添加成功返回客户列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void saveCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 取：
        String custName = req.getParameter("custName");
        String custMobile = req.getParameter("custMobile");
        String custAddress = req.getParameter("custAddress");
        Integer custTicket = Integer.parseInt(req.getParameter("custTicket"));
        // 调
        Customer customer = new Customer(-1,custName,custMobile,custAddress,custTicket);
        int rows = customerService.saveCustomer(customer);
        if (log.isDebugEnabled()) {
            log.debug("受影响的行数："+rows);
        }
        // 转：跳转到客户列表页面
        this.listCustomer(req,resp);
    }

    /**
     * 根据客户id删除客户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void deleteCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer cid = Integer.parseInt(req.getParameter("cid"));

        Integer rows = customerService.deleteCustomerById(cid);
        if (log.isDebugEnabled()) {
            log.debug("受影响的行数："+rows);
        }

        this.listCustomer(req, resp);
    }

    /**
     * 当客户点击修改按钮时，跳转到cust_update.jsp页面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void preUpdateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer cid = Integer.parseInt(req.getParameter("cid"));
        Customer customer = customerService.getCustomerById(cid);
        if (log.isDebugEnabled()) {
            log.debug("获取的用户信息："+customer.toString());
        }
        // 将获取的用户信息和水票种类信息传递给前端页面
        req.setAttribute("cust",customer);
        req.setAttribute("ticketSet",TICKET_SET);

        req.getRequestDispatcher("/WEB-INF/pages/cust_update.jsp").forward(req,resp);
    }

    /**
     * 当客户点击提交按钮时更新客户信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void updateCustomer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer cid = Integer.parseInt(req.getParameter("cid"));
        String custName = req.getParameter("custName");
        String custMobile = req.getParameter("custMobile");
        String custAddress = req.getParameter("custAddress");
        Integer custTicket = Integer.parseInt(req.getParameter("custTicket"));

        Customer customer = new Customer(cid,custName,custMobile,custAddress,custTicket);
        int rows = customerService.updateCustomer(customer);
        if (log.isDebugEnabled()) {
            log.debug("受影响的行数："+rows);
        }

        this.listCustomer(req, resp);
    }

}
