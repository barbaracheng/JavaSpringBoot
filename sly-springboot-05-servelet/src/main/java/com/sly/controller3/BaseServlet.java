package com.sly.controller3;

import javax.servlet.*;
import java.io.IOException;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/19 15:18
 */
public abstract class BaseServlet implements Servlet {
    private transient  ServletConfig config;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.config = servletConfig;
//        System.out.println("Third init........");
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

    }
}
