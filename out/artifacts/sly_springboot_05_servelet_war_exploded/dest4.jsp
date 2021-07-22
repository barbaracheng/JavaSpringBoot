<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2021/7/20
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    // 接收session_test.jsp共享的数据并显示
    out.print("dest4.jsp收到的数据："+session.getAttribute("message"));
%>
</body>
</html>
