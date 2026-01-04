<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Calendar" %>
<%@page import="com.demo.javabean.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OnlineLibrary</title>
    <link rel="stylesheet" href="css/head02.css">
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
    Object obj = session.getAttribute("a_user");
    if (obj == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>
<div class="sidebar">
    <ul>
        <!--  <li><a href="#">用户</a></li>-->
        <li><a href="userChoiceServlet.do?signal=1" target="showBook">全部图书</a></li>
        <li><a href="user/my_borrow.jsp" target="showBook">我的已借图书</a></li>
        <li><a href="user/my_message.jsp" target="showBook">我的基本信息</a></li>
        <li><a href="login.jsp">切换账号/退出</a></li>
    </ul>
</div>
<a class="btn"></a>
<% Calendar cal = Calendar.getInstance();
    SimpleDateFormat format = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm:ss");
%>
<!-- 关键修改：将 class="box" 改为 class="main-content"，解决布局错位 -->
<div class="main-content">
    <div class="box-head">图书管理系统</div>
    <p align="right">欢迎你，${a_name}</p>
    <!-- EL表达式 -->
    <p align="right">
        <% out.println(format.format(cal.getTime()));%>
    </p>
    <!-- <iframe src="user/my_message.jsp" frameborder="0" height="50px" width="100%" id="person_iframe"></iframe>  -->
    <iframe name="showBook" src="" frameborder="0" width="100%"
            height="80%"></iframe>
</div>
</body>
</html>