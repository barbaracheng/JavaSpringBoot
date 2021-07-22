package com.sly.controller3;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO:继承了BaseServlet，并覆写了service方法，在service方法中添加对请求类型的判断
 *
 * @author leyuan
 * @date 2021/7/19 15:21
 */
public class ThirdServlet extends BaseServlet {

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // 将ServletRequest和ServletResponse 转换为支持HTTP协议的请求和响应对象
        HttpServletRequest req =  (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        // 获取请求方法
        String method = req.getMethod();
        switch (method) {
            case "GET":
                doGet(req,res);
                break;
            case "POST":
                doPost(req,res);
                break;
            default:
                doGet(req,res);
                break;
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        this.doGet(req,res);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = res.getWriter();
        writer.print("Third, 你好，世界！");
    }
}
