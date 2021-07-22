package com.sly.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/19 17:26
 */
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter init...................");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 服务器响应结果到浏览器之前会进行拦截，重新设置响应给浏览器的字符集编码方式为UTF-8
        // response.setContentType("text/html;charset=UTF-8");
        // 获取web.xml配置文件的全局配置信息
        String contentType = servletRequest.getServletContext().getInitParameter("contentType");
        servletResponse.setContentType(contentType);
        // 拦截浏览器请求，在请求到达Servlet之前，设置请求的字符集编码为UTF-8
        servletRequest.setCharacterEncoding("UTF-8");
        // 处理完拦截之后放行（服务器响应给浏览器的字符集编码设置完毕了就放行）
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
