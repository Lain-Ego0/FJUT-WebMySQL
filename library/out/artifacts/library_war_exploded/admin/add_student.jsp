<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!--
<%@ page session="false" %>
-->
<html>
<head>
    <title>AddStudent</title>
    <link rel="stylesheet" href="../css/reg.css">
</head>

<body style="background-image: none;margin-top: 0px">
<div class="box-body">
    <form action="../manageStudentServlet.do?action=addstudent" method="post" name="form3">

        <table>
            <tr>
                <th>学生学号：</th>                           <!-- 输入用户名称 -->
                <td><input type="text" name="user"></td>
            </tr>
            <tr>
                <th>姓　　名：</th>
                <td><input type="text" name="name"></td>

            </tr>
            <tr>
                <th>密　　码：</th>                        <!-- 输入密码 -->
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <th>确认密码：</th>                           <!-- 重复密码 -->
                <td><input type="password" name="relpwd"></td>
            </tr>

            <tr>
                <th>年　　级：</th>
                <td><input type="text" name="grade"></td>

            </tr>
            <tr>
                <th>班级：</th>
                <td><input type="text" name="classes"
                ></td>
            </tr>
            <tr>
                <th>电子邮箱：</th>
                <td><input type="text" name="email"></td>
            </tr>
            <tr>
                <td>
                    <input type="hidden" name="admin" value="admin">
                </td>
            </tr>
        </table>
        <input type="submit" value="添加">
    </form>
</div>
</body>
</html>
