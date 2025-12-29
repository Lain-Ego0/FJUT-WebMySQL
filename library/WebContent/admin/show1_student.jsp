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
	<%
       // ArrayList<Students> students = new StudentDAO().getAllStudents();
    %>


	<h2><%="所有学生账户信息"%></h2>
	<form action="PageServlet.do" method="get">
		<input type="text" name="user" placeholder="请输入学生学号">
		<input type="text" name="name" placeholder="请输入学生姓名">
		<input type="text" name="method" value="showStudent" hidden>
		<input type="submit" value="查询">
	</form>


	<table>
		<tr>
			<td width="150px"><%="学生学号"%></td>
			<td width="150px"><%="学生姓名"%></td>
			<td width="150px"><%="学生年级"%></td>
			<td width="150px"><%="学生班级"%></td>
			<td width="150px"><%="操作"%></td>
		</tr>
		<%--<%
           ArrayList <Students> list=(ArrayList<Students>)session.getAttribute("list");
                for (Students student: list){
            --%>
		<c:forEach items="${list }" var="s">
			<tr>
				<%--  <td><%=student.getUser()%></td>
            <td><%=student.getName()%></td>
            <td><%=student.getGrade()%></td>
             <td><%=student.getClasses()%></td>--%>
				<td>${s.user}</td>
				<td>${s.name}</td>
				<td>${s.grade}</td>
				<td>${ s.classes}</td>
				<td><a
					href="manageStudentServlet.do?action=showstudent&user=${s.user}">详情</a></td>
			</tr>
		</c:forEach>
		<%
       // }
    %>
		<tr>
			<td align="center" colspan="6" class="pagination">
				<%--<%=request.getAttribute("bar")  /页 --%> ${bar}/页
			</td>
		</tr>
	</table>
	</div>
</body>
</html>