<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPage</title>
    <link rel="stylesheet" href="css/head02.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/style.css">

    <style>


    </style>
</head>
<body>
<div class="sidebar">
    <ul>
        <li><a href="admin.jsp">首页</a></li>
        <li><a href="admin/add_student.jsp" target="showBook">添加学生</a></li>
        <li><a href="PageServlet.do?method=showStudent" target="showBook">查看学生</a></li>
        <li><a href="PageServlet.do?method=delStudent" target="showBook">删除学生</a></li>
        <li><a href="admin/reg_book.jsp" target="showBook">添加书籍</a></li>
        <li><a href="PageServlet.do?method=showBook" target="showBook">查看书籍</a></li>
        <!--<li><a href="admin/del_book.jsp" target="showBook">删除书籍</a></li> -->
        <li><a href="login.jsp">切换账号/退出</a></li>
    </ul>
</div>
<% Calendar cal = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("yyyy年-MM月-dd日");
%>
<!-- 关键修改：将 class="box" 改为 class="main-content"，解决布局错位 -->
<div class="main-content">
    <div class="box-head" ><h1>图书管理系统</h1></div>
    <!-- 用户注册的标题 -->
    <p align="right">欢迎你，管理员 </p>
    <p align="right"><% out.println(format.format(cal.getTime()));%></p>
    <iframe name="showBook" src="" frameborder="0" width="100%" height="80%"></iframe>
</div>
</body>
</html>