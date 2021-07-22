package com.sly.controller4;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO:继承了AbstractServlet类
 * BaseServlet用来模拟HttpServlet，覆写service()方法
 * 根据不同的请求方式调用不同的方法处理
 * @author leyuan
 * @date 2021/7/19 15:18
 */
public abstract class BaseServlet extends AbstractServlet {
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
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
    }
}
