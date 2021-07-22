<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2021/7/20
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
    把Bootstrap框架导入到JSP页面
    Bootstrap样式不生效，在Maven中clean一次，将之前的加载的资源清理掉，重新加载
    href="${}" 表示应用的URI支持EL表达式
    pageContext.request.contextPath 获取当前项目的上下文路径
    --%>
<html>
<head>
    <title>客户信息列表</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
</head>
<body>
    <div class="container" style="margin: 50px auto;">
        <div class="row">
            <div style="width:800px;margin: 0 auto;">
                <div class="col-md-4">
                    <a class="btn btn-primary" href="/cust4?method=preSaveCustomer">添加客户</a>
                </div>
                <form class="form-inline col-md-8" method="post" action="/cust4?method=searchCustomer">
                    <div class="form-group">
                        <input type="text" required class="form-control" placeholder="请输入客户姓名..." name="custName">
                    </div>
                    <button type="submit" class="btn btn-danger">搜索</button>
                </form>
                <table class="table table-hover table-bordered table-striped">
                    <thead>
                        <tr style="background-color:#fc9595;">
                            <th>客户编号</th>
                            <th>客户姓名</th>
                            <th>联系方式</th>
                            <th>客户地址</th>
                            <th>客户水票</th>
                            <th>编辑</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${custList}" var="cust">
                            <tr>
                                <td>${cust.cid}</td>
                                <td>${cust.custName}</td>
                                <td>${cust.custMobile}</td>
                                <td>${cust.custAddress}</td>
                                <td>${cust.custTicket}</td>
                                <td>
                                    <a class="btn btn-sm btn-primary" href="/cust4?method=preUpdateCustomer&cid=${cust.cid}">修改</a>
                                    <a class="btn btn-sm btn-warning" href="/cust4?method=deleteCustomer&cid=${cust.cid}">删除</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
