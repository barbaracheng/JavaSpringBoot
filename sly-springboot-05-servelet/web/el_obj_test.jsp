<%@ page import="com.sly.entities.Customer" %><%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2021/7/20
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        Customer customer = new Customer(6,"小明","12343218970","香港",80);
        session.setAttribute("cust",customer);
    %>
    客户编号：${cust.cid}</br>
    客户姓名：${cust.custName}</br>
    客户手机号：${cust.custMobile}</br>
    客户地址：${cust.custAddress}</br>
    客户水票：${cust.custTicket}</br>
</body>
</html>
