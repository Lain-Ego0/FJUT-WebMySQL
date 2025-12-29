<%@ page import="com.demo.javabean.Books" %>
<%@ page import="com.demo.dao.BookDAO" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
//String path = request.getContextPath();
//String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>Update Book</title>
    <style>
        h2 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        table {
            width: 80%;
            margin: 0 auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }

        tr:hover {
            background-color: #f5f5f5;
        }
        input[type="submit"] {
            display: block;
            width: 30%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

    </style>
</head>
<body style="background-image: none" >
    <div class="box-body" id="box-body" style="overflow-y: auto;"> 
        <form action="manageBookServlet.do?action=updatebook" method="post" >
        <input type="hidden" name="sid" value="${book.id}"/>
            <table  >
         <%
             //1.要设置value的值为之前内容，就需要，servlet重定向，把原来数据先添加进去，
             //2.servlet里面接收新设置的内容，设置book的各个参数，调用bookdao update 刷新图书内容 ,这里采用EL表达式
                %>
                <tr >
                    <th>图书名称: </th>
                  <td><input type="text" name="name" value="${book.name}"></td>
                    
                </tr>
                <tr>
                    <th>图书作者: </th>
                    <td ><input type="text" name="author"  value="${book.author}"></td>
                </tr>
                <tr>
                    <th>图书数量: </th>
                    <td><input type="text" name="amount"  value="${book.amount}"></td>
                </tr>
                <tr>
                    <th>图书类型: </th>
                    <td ><input type="text" name="category"  value="${book.category}"></td>
                </tr>
            </table>
            <br>
            <input type="submit"  value="提交">
        </form>
      </div>
  </body>
</html>
