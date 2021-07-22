package com.sly.login;

import com.sly.common.BaseServlet;
import com.sly.entities.Account;
import com.sly.entities.Customer;
import com.sly.service.CustomerService;
import com.sly.service.LoginService;
import com.sly.service.impl.CustomerServiceImpl;
import com.sly.service.impl.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/21 17:17
 */
@WebServlet(urlPatterns = "/login")
@Slf4j
public class LoginServlet extends BaseServlet {
    private LoginService loginService = new LoginServiceImpl();

    private CustomerService customerService = new CustomerServiceImpl();

    /**
     * 处理客户登录的逻辑
     * 步骤：
     * 1取：表单采集的用户名和密码
     * 2调：调用用户登录的业务逻辑
     * 3转: 登录成功跳转到客户列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String pwd = req.getParameter("userPwd");

        Account account = loginService.login(userName, pwd);
        if (null != account) {
            List<Customer> customerList = customerService.listCustomer();
            if (log.isDebugEnabled()) {
                log.debug(customerList.toString());
            }
            // 将客户信息传到前端页面
            req.setAttribute("custList",customerList);
            // 重定向到cust_list.jsp
            req.getRequestDispatcher("/WEB-INF/pages/cust_list.jsp").forward(req,resp);
        } else {
            resp.getWriter().print("登录错误");
        }
    }

}
