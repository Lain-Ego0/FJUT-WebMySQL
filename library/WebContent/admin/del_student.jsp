<%@ page import="com.demo.javabean.Students"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.demo.dao.StudentDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!-- 引入JSTL标签库 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>All Student</title>
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
	<!-- <div class="box-body" id="box-body" style="overflow-y: auto;"> -->

	<h2><%="所有学生账户信息"%></h2>
	<table>
		<tr>
			<td width="110px"><%="学生学号"%></td>
			<!--  <td width="110px"><%="学生密码"%></td> -->
			<td width="110px"><%="学生姓名"%></td>
			<td width="110px"><%="学生年级"%></td>
			<td width="110px"><%="学生班级"%></td>
			<!--  <td width="130px"><%="电子邮箱"%></td>-->
			<!--  <td width="80px"><%="借书数量"%></td>-->
			<td width="80px"><%="操作"%></td>
		</tr>
		<c:forEach items="${list}" var="s">
			<!-- forEach遍历封装在session中的list集合 -->
			<!--   <%  
               //ArrayList <Students> list=(ArrayList<Students>)session.getAttribute("list");
            
              //int a=list.size();
             // for(Students student:list){
            %>-->
			<tr>
				<td>${s.user}</td>
				<!-- <td>${s.password}</td> -->
				<td>${s.name}</td>
				<td>${s.grade}</td>
				<td>${s.classes}</td>
				<!-- <td></td> -->
				<!-- <td></td>-->
				<td><a
					href="manageStudentServlet.do?action=delstudent&user=${s.user}"
					onclick="return confirm('确定删除？');">删除</a></td>
		</c:forEach>
		</tr>

		<%
      //  }
    %>
		<tr>
			<td align="center" colspan="6" class="pagination">
				<!-- <%=request.getAttribute("bar")   %>/页 --> ${bar}/页
			</td>
		</tr>
		</div>
</body>
</html>
