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
			<td>${student.email == null ? '未填写' : student.email}</td>
		</tr>
		<%-- 新增：我的借书证号 --%>
		<tr>
			<td>我的借书证号:</td>
			<td>${student.card_id == null ? '未填写' : student.card_id}</td>
		</tr>
		<%-- 新增：我的所在单位 --%>
		<tr>
			<td>我的所在单位:</td>
			<td>${student.unit == null ? '未填写' : student.unit}</td>
		</tr>
		<%-- 新增：我的职业 --%>
		<tr>
			<td>我的职业:</td>
			<td>${student.occupation == null ? '未填写' : student.occupation}</td>
		</tr>
		<tr>
			<td>可借书籍:</td>
			<td>${8-student.amount}</td>
		</tr>
		<tr>
			<td>已借书籍:</td>
			<td>${student.amount}</td>
		</tr>
	</table>
	<%
	} else {
		// 无学生信息时的提示
	%>
	<h3 style="text-align:center; color:#ff4444;">未获取到个人信息，请先登录！</h3>
	<%
		}
	%>
</div>
</body>
</html>
