<%@ page import="com.demo.javabean.Books" %>
<%@ page import="com.demo.dao.BookDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>所有图书信息</title>
    <style>
        /* 保留原有样式 */
        body { font-family: Arial, sans-serif; }
        h2 { margin-top: 20px; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }
        th { background-color: #4CAF50; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        tr:hover { background-color: #ddd; }
        td a { color: #4CAF50; text-decoration: none; }
        td a:hover { color: #2196F3; }
        td.pagination { text-align: center; padding: 10px; background-color: #f8f9fa; border: 1px solid #dee2e6; font-weight: bold; }
    </style>
</head>
<body>
<div class="box-body" id="box-body" style="overflow-y: auto;">
    <h2>所有图书信息</h2>
    <!-- 保留原有查询表单提交路径 -->
    <form action="PageServlet.do" method="get">
        <input type="text" name="name" placeholder="请输入图书名称">
        <input type="text" name="method" value="showBook" hidden>
        <input type="submit" value="查询">
    </form>
    <table>
        <tr>
            <td width="100px">ID</td>
            <td width="200px">图书名称</td>
            <td width="150px">ISBN</td> <!-- 新增 -->
            <td width="150px">图书作者</td>
            <td width="150px">出版社</td> <!-- 新增 -->
            <td width="100px">定价</td> <!-- 新增 -->
            <td width="200px">简介</td> <!-- 新增 -->
            <td width="150px">图书类别</td>
            <td width="100px">图书数量</td>
            <td width="150px">操作</td>
        </tr>
        <!-- 保留原有请求属性名list（与后端一致） -->
        <c:forEach items="${list}" var="b">
        <tr>
            <td>${b.id}</td>
            <td>${b.name}</td>
            <td>${b.isbn}</td> <!-- 新增，对应Books.isbn -->
            <td>${b.author}</td>
            <td>${b.publisher}</td> <!-- 新增，对应Books.publisher -->
            <td>${b.price}</td> <!-- 新增，对应Books.price -->
            <td>${b.intro}</td> <!-- 新增，对应Books.intro -->
            <td>${b.category}</td>
            <td>${b.amount}</td>
            <!-- 保留原有操作链接路径（与后端manageBookServlet.do兼容） -->
            <td>
                <a href="manageBookServlet.do?action=update&sid=${b.id }">更改</a>
                <a href="manageBookServlet.do?action=delbook&name=${b.name }" onclick="return confirm('确定删除？');">删除</a>
            </td>
        </tr>
        </c:forEach>
        <tr>
            <td class="pagination" align="center" colspan="10">
                ${bar}/页 <!-- 保留分页栏 -->
            </td>
        </tr>
</div>
</body>
</html>