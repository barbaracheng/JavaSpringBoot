<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2021/7/20
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Session test</title>
</head>
<body>
    <%
        // 在多个页面之间设置共享数据
        session.setAttribute("message","hello.");
        // 重定向到dest3.jsp
        response.sendRedirect("/dest3.jsp");
    %>
</body>
</html>
