<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2021/7/20
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setAttribute("age",21);
    %>
    age = ${age}
</body>
</html>
