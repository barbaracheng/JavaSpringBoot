package com.sly.controller1;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO:自定义Servlet实现Servlet接口
 * @author leyuan
 * @date 2021/7/19 10:10
 */
public class FirstServlet implements Servlet {
    private transient  ServletConfig config;

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
//        System.out.println(this+" init.................");
    }

    @Override
    public ServletConfig getServletConfig() {
//        System.out.println("getServletConfig....................");
        return config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter writer = servletResponse.getWriter();
        writer.print("HelloWorld...............");
//        System.out.println(this+" service....................");
    }

    @Override
    public String getServletInfo() {
//        System.out.println("getServletInfo..................");
        return config.getServletName();
    }

    @Override
    public void destroy() {
//        System.out.println(this+"destroy.................");
    }
}
