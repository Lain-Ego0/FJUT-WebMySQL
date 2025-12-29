<%@ page import="com.demo.javabean.Students"%>
<%@ page import="com.demo.dao.StudentDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>My Message</title>
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

	</style>
</head>
<body>
	<%
        StudentDAO s_dao = new StudentDAO();
        Students student = (Students)session.getAttribute("student");
        if(student != null){
            student = s_dao.getStudentByName(student.getUser());
            session.setAttribute("student",student);
    %>
	<div id="my_message">
		<h2><%="我的基本信息"%></h2>
		<table>
			<%--   <tr><td width="120px">我的学号:</td><td><%=student.getUser()%></td></tr>
                   <!--   <tr><td>我的密码:</td><td><%=student.getPassword()%></td></tr>-->
                     <tr><td>我的系科:</td><td><%=student.getName()%></td></tr>
                    <tr><td>我的年级:</td><td><%=student.getGrade()%></td></tr>
                    <tr><td>我的班级:</td><td><%=student.getClasses()%></td></tr>
                    <tr><td>我的邮箱:</td><td><%=student.getEmail()%></td></tr>
                    <tr><td>可借书籍:</td><td><%=10 - student.getAmount()%></td></tr>
                    <tr><td>已借书籍:</td><td><%=student.getAmount()%></td></tr>--%>
			<tr>
				<td width="120px">我的学号:</td>
				<td>${student.user}</td>
			</tr>
			<tr>
				<td>我的姓名:</td>
				<td>${student.name}</td>
			</tr>
			<tr>
				<td>我的年级:</td>
				<td>${student.grade}</td>
			</tr>
			<tr>
				<td>我的班级:</td>
				<td>${student.classes}</td>
			</tr>
			<tr>
				<td>我的邮箱:</td>
				<td>${student.email}</td>
			</tr>
			<tr>
				<td>可借书籍:</td>
				<td>${10-student.amount}</td>
			</tr>
			<tr>
				<td>已借书籍:</td>
				<td>${student.amount}</td>
			</tr>
		</table>
		<%
            }
        %>
	</div>
</body>
</html>
