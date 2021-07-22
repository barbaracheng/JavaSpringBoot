package com.sly.controller2;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO: 自定义的servlet继承了AbstractServlet
 * 此时只需要覆写父类的service方法，与之前相比做了很大的改进，只需要覆写一个方法
 *
 * @author leyuan
 * @date 2021/7/19 15:09
 */
public class SecondServlet extends AbstractServlet{
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        // 设置输出到浏览器的响应内容的类型，目的：解决乱码
        // text/html 表示响应给浏览器的类型是文本
        // charset=UTF-8 表示响应给浏览器的字符集编码为utf-8
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        writer.print("Second,你好！世界");
    }
}
