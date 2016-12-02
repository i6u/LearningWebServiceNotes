<%--
  Created by IntelliJ IDEA.
  User: zhouweitao
  Date: 2016/12/1
  Time: 下午1:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>user manage login</title>
    <style type="text/css">
        .bd{
            width: 300px;
        }
        h3{
            margin: 20px 0px;
        }
        h3 a {
            text-decoration: none;
            background: #ffc9b1;
            color: #fff;
            padding: 5px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="bd">
    <h3><a href="<%=request.getContextPath()%>/user?m=list">只浏览用户信息</a></h3>
    <form action="<%=request.getContextPath()%>/user?m=login" method="post">
        <fieldset>
            <legend>user login</legend>
            姓名：<input type="text" name="username"/><br/>
            密码：<input type="password" name="password"/><br/>
            <input type="submit" value="登录">
        </fieldset>
    </form>
</div>
</body>
</html>
