package com.sly.controller4;

import javax.servlet.*;
import java.io.IOException;

/**
 * TODO:AbstractServlet模拟Servlet源码的GenericServlet，此时覆写除了service()方法以外的所有方法
 * 该类是一个抽象类
 *
 * @author leyuan
 * @date 2021/7/19 15:05
 */
abstract public class AbstractServlet implements Servlet {
    private transient ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("Second init....................");
        this.config = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return config;
    }

    abstract public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException;


    @Override
    public String getServletInfo() {
        return config.getServletName();
    }

    @Override
    public void destroy() {
        System.out.println("Destory...........................");
    }
}
