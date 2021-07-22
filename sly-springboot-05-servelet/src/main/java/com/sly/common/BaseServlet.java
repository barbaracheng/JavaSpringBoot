package com.sly.common;

import com.sly.jdbc.utils.DruidUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TODO: BaseSevlet是所有自定义Servlet类的父类，它相当于一个派发器，根据浏览器的请求派发给不同的子类处理
 *
 * @author leyuan
 * @date 2021/7/20 10:03
 */
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取浏览器请求的参数，根据method键获取对应的值
        String method = req.getParameter("method");
        PrintWriter pw = resp.getWriter();
        try {
            // 使用反射获取方法签名
            Method declaredMethod = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.invoke(this,req,resp);
//            System.out.println("获取的方法签名为："+declaredMethod);
        } catch (Exception e) {
            pw.print("请求方法错误");
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化Druid
        Class<DruidUtils> clazz = DruidUtils.class;
        System.out.println("Druid clazz"+clazz);
        super.init(config);
    }
}
