<%@ page import="com.demo.javabean.Books" %>
<%@ page import="com.demo.dao.BookDAO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.awt.print.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 引入JSTL标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Show Book</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h2 {
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        td a {
            color: #4CAF50;
            text-decoration: none;
        }

        td a:hover {
            color: #2196F3;
        }
        td.pagination {
            text-align: center;
            padding: 10px;
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="box-body" id="box-body" style="overflow-y: auto;">
        <h2><%="所有图书信息"%></h2>
        <form action="PageServlet.do" method="get">
            <input type="text" name="name" placeholder="请输入图书名称">
            <input type="text" name="method" value="showBook" hidden>
            <input type="submit" value="查询">
        </form>
        <table>
            <tr>
                <td width="250px"><%="图书名称"%></td>
                <td width="200px"><%="图书作者"%></td>
                <td width="150px"><%="图书类别"%></td>
                <td width="150px"><%="图书数量"%></td>
                 <td width="150px">&nbsp &nbsp<%="操作"%></td>
            </tr>
              <%--  <%
                ArrayList<Books>list=(ArrayList<Books>)request.getAttribute("list");
                    for (Books book:list){
                %>--%>
                <c:forEach items="${list}" var="b">
            <tr>
              <%--   <td><%=book.getName()%></td>--%>
              <td>${ b.name}</td>
               <td>${b.author}</td>
                <td>${b.category }</td>
                 <td>${b.amount }</td>
                 <%-- <td><%=book.getAuthor()%></td>--%>
                <%--  <td><%=book.getCategory()%></td>--%>
               <%--   <td><%=book.getAmount()%></td>--%>
				  <td><a href="manageBookServlet.do?action=update&sid=${b.id }">更改</a>
			<a href="manageBookServlet.do?action=delbook&name=${b.name }" onclick="return confirm('确定删除？');">删除</a>
			</td>
            </tr>
            </c:forEach>
                <%
            //}
        %>
        <tr>
    <td class="pagination" align="center" colspan="6" >
     <%-- <%=request.getAttribute("bar")   %>/页--%>
     ${bar}/页
    </td>
    </tr>
    </div>
</body>
</html>
