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
<%
    User user = (User) request.getSession().getAttribute("loginUser");


%>
<h3><%
    if (user != null) {
%>
    当前登录的用户：<%=user.getNickname()%>
    <%
    } else {
    %>
    匿名用户只能浏览不能操作！
    <%
        }
    %></h3>
<table>
    <tr>
        <td>姓名</td>
        <td>密码</td>
        <td>别名</td>
        <%
            if (user != null) {
        %>
        <td>操作</td>
        <%
            }
        %>
    </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        for (User u : users) {
    %>
    <tr>
        <td><%=u.getUsername()%>
        </td>
        <td><%=u.getPassword()%>
        </td>
        <td><%=u.getNickname()%>
        </td>
        <%
            if (user != null) {
        %>
        <td><a href="<%=request.getContextPath()%>/user?m=del&username=<%=u.getUsername()%>">删除</a></td>
        <%
            }
        %>
    </tr>
    <%
        }
    %>
    <tr>
        <%
            if (user != null) {
        %>

        <td colspan="2">
            <button onclick="window.location='<%=request.getContextPath()%>/user?m=addInput'">add user</button>
        </td>
        <td colspan="2">
            <button onclick="window.location='<%=request.getContextPath()%>/user?m=loginOut'">login out</button>
        </td>

        <%
        } else {
        %>
        <td colspan="3">
            <button onclick="window.location='<%=request.getContextPath()%>/user?m=loginOut'">login</button>
        </td>
        <%
            }
        %>
    </tr>

</table>
</body>
</html>
