<%@ page import="java.util.List" %>
<%@ page import="zyr.learn.service.User" %><%--
  Created by IntelliJ IDEA.
  User: zhouweitao
  Date: 2016/12/1
  Time: 下午7:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>user list</title>
</head>
<body>
<h3><%=request.getSession().getAttribute("loginUser")%></h3>
<table>
    <tr>
        <th>姓名</th>
        <th>密码</th>
        <th>别名</th>
        <th>操作</th>
    </tr>
<%
    List<User> users = (List<User>) request.getAttribute("users");
    for (User u : users) {
%>
    <tr>
        <td><%=u.getUsername()%></td>
        <td><%=u.getPassword()%></td>
        <td><%=u.getNickname()%></td>
        <td><a href="<%=request.getContextPath()%>/user?m=del&username=<%=u.getUsername()%>">删除</a></td>
    </tr>
<%
    }
%>
    <tr>
        <td><button onclick="window.location='<%=request.getContextPath()%>/user?m=addInput'">add user</button></td>
    </tr>
</table>
</body>
</html>
