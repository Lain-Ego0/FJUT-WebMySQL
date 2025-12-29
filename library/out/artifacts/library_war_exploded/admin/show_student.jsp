<%@ page import="com.demo.javabean.Students"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.demo.dao.StudentDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Personal Student</title>
	<link rel="stylesheet" href="../css/list.css">
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
	</style>
</head>
<body>
	<!-- <div class="box-body" id="box-body" style="overflow-y: auto;"> -->
	<%--<%
        Students students=(Students)request.getAttribute("student");
    --%>
	<h2><%="该生账户信息"%></h2>
	<table>
		<tr>
			<%-- <td width="110px" >学生学号 :&nbsp&nbsp&nbsp<%=students.getUser()%></td>
		</tr>
		<tr>
		<td width="110px">学生密码 :&nbsp&nbsp&nbsp<%=students.getPassword()%></td>
		</tr>
		<tr>
			<td width="140px">学生姓名:&nbsp&nbsp&nbsp<%=students.getName()%></td>
		</tr>
		<tr>
			<td width="110px">学生年级:&nbsp&nbsp&nbsp<%=students.getGrade()%></td>
		</tr>
		<tr>
			<td width="110px">学生班级:&nbsp&nbsp&nbsp<%=students.getClasses()%></td>
		</tr>
		<tr>
			<td width="280px">电子邮箱:&nbsp&nbsp&nbsp<%=students.getEmail()%></td>
		</tr>
		<tr>
			<td width="80px">借书数量:&nbsp&nbsp&nbsp<%=students.getAmount()%></td>--%>
			<!-- EL表达式 输出信息 -->
			<td width="110px">学生学号 :&nbsp&nbsp&nbsp ${student.user}</td>
		</tr>
		<tr>
			<td width="110px">学生密码 :&nbsp&nbsp&nbsp ${student.password }</td>
		</tr>
		<tr>
			<td width="140px">学生姓名:&nbsp&nbsp&nbsp ${student.name}</td>
		</tr>
		<tr>
			<td width="110px">学生年级:&nbsp&nbsp&nbsp ${student.grade }</td>
		</tr>
		<tr>
			<td width="110px">学生班级:&nbsp&nbsp&nbsp ${ student.classes}</td>
		</tr>
		<tr>
			<td width="280px">电子邮箱:&nbsp&nbsp&nbsp ${ student.email}</td>
		</tr>
		<tr>
			<td width="80px">借书数量:&nbsp&nbsp&nbsp ${student.amount }</td>
		</tr>
	</table>
</body>
</html>
