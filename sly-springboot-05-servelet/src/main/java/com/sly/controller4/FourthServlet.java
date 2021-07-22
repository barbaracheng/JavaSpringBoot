package com.sly.controller4;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TODO:继承了BaseServlet类，只用覆写doGet和doPost方法
 *
 * @author leyuan
 * @date 2021/7/19 15:35
 */
public class FourthServlet extends BaseServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        this.doGet(req,res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = res.getWriter();
        writer.print("Fourth，你好，世界！");
    }
}
