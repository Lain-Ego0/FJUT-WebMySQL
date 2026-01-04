<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
    <link rel="stylesheet" href="../css/head02.css">
</head>
<body>
<div class="student-form">
    <h3>添加学生信息</h3>
    <!-- 修复1：补action=addstudent参数；修复2：参数名与Servlet统一 -->
    <form action="AddStudentServlet.do?action=addstudent" method="post">
        <div class="form-row">
            <label>学号：</label>
            <input type="text" name="user" required> <!-- 对应Servlet的user（原studentId） -->
        </div>
        <div class="form-row">
            <label>姓名：</label>
            <input type="text" name="name" required> <!-- 对应Servlet的name（原studentName） -->
        </div>
        <div class="form-row">
            <label>借书证号：</label>
            <input type="text" name="cardId" required> <!-- 对应Students.card_id -->
        </div>
        <div class="form-row">
            <label>密码：</label>
            <input type="password" name="password" required> <!-- 保留原参数名 -->
        </div>
        <div class="form-row">
            <label>确认密码：</label>
            <input type="password" name="relpwd" required> <!-- 新增：匹配Servlet的relpwd -->
        </div>
        <div class="form-row">
            <label>电话：</label>
            <input type="text" name="phone" required> <!-- 保留原参数名 -->
        </div>
        <div class="form-row">
            <label>院系：</label>
            <input type="text" name="department" required> <!-- 保留原参数名 -->
        </div>
        <div class="form-row">
            <label>所在单位：</label>
            <input type="text" name="unit"> <!-- 对应Students.unit -->
        </div>
        <div class="form-row">
            <label>职业：</label>
            <input type="text" name="occupation"> <!-- 对应Students.occupation -->
        </div>
        <div class="form-row">
            <label>年级：</label>
            <input type="text" name="grade" required> <!-- 匹配Servlet的grade -->
        </div>
        <div class="form-row">
            <label>班级：</label>
            <input type="text" name="classes" required> <!-- 匹配Servlet的classes -->
        </div>
        <div class="form-row">
            <label>邮箱：</label>
            <input type="email" name="email" required> <!-- 匹配Servlet的email -->
        </div>
        <div class="btn-group">
            <input type="submit" value="提交">
            <input type="reset" value="重置">
        </div>
    </form>
</div>
</body>
</html>