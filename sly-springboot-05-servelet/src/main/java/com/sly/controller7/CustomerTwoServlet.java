package com.sly.controller7;

import com.sly.entities.Customer;
import com.sly.service.CustomerService;
import com.sly.service.impl.CustomerServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO:使用注解配置servlet，在浏览器上显示数据
 * @WebServlet是一个注解，告诉Tomcat服务器被该注解修饰的类是一个Servlet，一旦Tomcat发现这个注解就会使用反射去创建该类的对象
 * 我们就不用再去web.xml文件中注册了
 * @author leyuan
 * @date 2021/7/20 9:56
 */
@WebServlet(urlPatterns = "/cust",loadOnStartup = 2)
public class CustomerTwoServlet extends HttpServlet {
    private CustomerService customerService = new CustomerServiceImpl();

    /**
     * 服务器根据浏览器的请求，展示某一行客户信息到浏览器
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 取：取浏览器的参数cid。cid作为key，根据key获取对应的值
        Integer cid = Integer.parseInt(req.getParameter("cid"));
        System.out.println("cid ==="+cid);
        // 调：调用业务逻辑层对象CustomerService根据id查询对应的客户信息
        Customer customer = customerService.getCustomerById(cid);
        PrintWriter pw = resp.getWriter();
        // 转：将业务逻辑层对象查询的结果转发(通知)到浏览器
        pw.print(customer);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//        System.out.println("CustomerTwoServlet init ..................");
    }
}
