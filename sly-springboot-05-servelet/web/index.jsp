<%@ page import="java.util.Date" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
  <head>
    <title>login</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style2.css"/>
  </head>
  <body>
  <div class="main">
    <div class="login-form">

      <h1>玉汝于成公司后台管理系统-用户登录</h1>
      <div class="head">
        <img src="${pageContext.request.contextPath}/images/user.png" alt="用户头像"/>
      </div>
      <form action="/login?method=login" method="post">
        <input type="text" class="text" name="userName" required placeholder="请输入用户名">
        <input type="password" name="userPwd" required placeholder="请输入密码">
        <div class="submit">
          <input type="submit" name="submit" value="登录">
        </div>
        <p><a href="#">Forgot Password ?</a></p>

      </form>
    </div>
    <div class="copy-right">
      <p>Copyright &copy; 2021.www.leyuan.com All rights reserved.</p>
    </div>
  </div>
  <div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
  </body>
</html>
