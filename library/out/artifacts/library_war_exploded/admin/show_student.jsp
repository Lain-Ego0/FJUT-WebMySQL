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
		/* 新增：逾期数量标红突出 */
		.overdue {
			color: #ff4444;
			font-weight: bold;
		}
	</style>
</head>
<body>
<h2><%="该生账户信息"%></h2>
<table>
	<tr>
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
	<%-- 新增：借书证号字段 --%>
	<tr>
		<td width="110px">借书证号:&nbsp&nbsp&nbsp ${student.card_id }</td>
	</tr>
	<%-- 新增：所在单位字段 --%>
	<tr>
		<td width="110px">所在单位:&nbsp&nbsp&nbsp ${student.unit }</td>
	</tr>
	<%-- 新增：职业字段 --%>
	<tr>
		<td width="110px">职业:&nbsp&nbsp&nbsp ${student.occupation }</td>
	</tr>
	<tr>
		<td width="80px">借书数量:&nbsp&nbsp&nbsp ${student.amount }</td>
	</tr>
	<%-- 新增：逾期未还图书数量 --%>
	<tr>
		<td width="110px" class="overdue">逾期未还数量:&nbsp&nbsp&nbsp ${overdueCount }</td>
	</tr>
</table>
</body>
</html>