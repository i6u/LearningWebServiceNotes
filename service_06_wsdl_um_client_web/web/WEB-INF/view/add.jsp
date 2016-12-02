<%--
  Created by IntelliJ IDEA.
  User: zhouweitao
  Date: 2016/12/1
  Time: 下午7:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>add user</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/user?m=add" method="post">
    <fieldset>
        <legend>add user</legend>
        姓名：<input type="text" name="username"/>
        密码：<input type="text" name="password"/>
        昵称：<input type="text" name="nickname"/>
        <input type="submit" value="确认添加">
    </fieldset>
</form>
</body>
</html>
