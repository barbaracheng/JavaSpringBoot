<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2021/7/21
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改客户信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
</head>
<body>
<div class="container" style="margin: 50px auto;">
    <div class="row">
        <div class="col-md-12">
            <form class="form-horizontal" method="post" action="/cust4?method=updateCustomer">
                <input type="hidden" name="cid" value="${cust.cid}">
                <div class="form-group">
                    <label for="inputName" class="col-sm-2 control-label">客户名称</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="inputName" name="custName" value="${cust.custName}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="Mobile" class="col-sm-2 control-label">联系方式</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="Mobile" name="custMobile" value="${cust.custMobile}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="Address" class="col-sm-2 control-label">客户地址</label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="Address" name="custAddress" value="${cust.custAddress}">
                    </div>
                </div>
                <div class="form-group">
                    <label for="Ticket" class="col-sm-2 control-label">客户水票</label>
                    <div class="col-sm-4">
                        <select class="form-control" name="custTicket" id="Ticket">
                            <c:forEach items="${ticketSet}" var="ticket">
                                <c:choose>
                                    <%-- 条件成立：将水票数量的选项设置为选中状态--%>
                                    <c:when test="${ticket eq cust.custTicket}">
                                        <option value="${ticket}" selected>${ticket}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${ticket}">${ticket}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4 col-sm-offset-2">
                        <div style="display: flex;flex-flow: row nowrap;justify-content: space-between;" >
                            <input id="submit" type="submit" class="btn btn-primary" value="提交"/>
                            <a class="btn-default btn" href="/cust4?method=listCustomer">返回</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
