<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2021/7/21
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加客户</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css"/>
</head>
<body>
    <div class="container" style="margin: 50px auto;">
        <div class="row">
            <div class="col-md-12">
                <form class="form-horizontal" method="post" action="/cust4?method=saveCustomer">
                    <div class="form-group">
                        <label for="inputName" class="col-sm-2 control-label">客户名称</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="inputName" name="custName" placeholder="例如：xxx">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Mobile" class="col-sm-2 control-label">联系方式</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="Mobile" name="custMobile" placeholder="例如：18216033522">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Address" class="col-sm-2 control-label">客户地址</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="Address" name="custAddress" placeholder="例如：武汉">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="Ticket" class="col-sm-2 control-label">客户水票</label>
                        <div class="col-sm-4">
                            <select class="form-control" name="custTicket" id="Ticket">
                                <option value="10">10</option>
                                <option value="20">20</option>
                                <option value="70">30</option>
                                <option value="30">50</option>
                                <option value="40">100</option>
                                <option value="50">200</option>
                                <option value="60">500</option>

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

    <script>
        var inputSubmit = document.getElementById("submit");
        inputSubmit.addEventListener('click',function(){
            alert('添加成功');
        });

    </script>
</body>
</html>
