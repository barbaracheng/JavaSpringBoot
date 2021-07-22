<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2021/7/20
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        pageContext.setAttribute("food","海苔饼");
        request.setAttribute("food","薯片");
        session.setAttribute("food","奶茶");
        application.setAttribute("food","面条");
    %>
    EL表达式的默认输出：${food}<br>
    显式指定作用域的结果如下：<br>

    <table style="border:3px solid black;">
        <tr>
            <th>食物</th>
        </tr>
        <tr>
            <td>${food}</td>
        </tr>
        <tr>
            <td>${pageScope.food}</td>
        </tr>
        <tr>
            <td>${sessionScope.food}</td>
        </tr>
        <tr>
            <td>${applicationScope.food}</td>
        </tr>
    </table>
</body>
</html>
