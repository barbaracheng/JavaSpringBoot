<%--
EL 表达式支持的基本数据类型
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    整数：${89},${19}<br>
    布尔值：${true},${false}<br>
    小数：${12.14}<br>
    <%--  El表达式支持基本的算术运算  --%>
    12+15=${12+15}<br>
    "12"+"15"=${"12"+"15"}<br>
    <%-- 12a + 15 会报错，因为Java会试图把12a转换为数字，而12a转换不成数字--%>

</body>
</html>
