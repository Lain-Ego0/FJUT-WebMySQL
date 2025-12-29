<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
    <link rel="stylesheet" type="text/css" href="css/reg.css">
</head>
<body>
<button onclick="window.location.href='login.jsp'" class="return">返
    回
</button>
<!-- 注册页面左上角的返回按钮，点击时返回登录页面 -->
<div class="box">
    <div class="box-head">学 生 信 息 注 册</div>
    <!-- 用户注册的标题 -->
    <br>
    <div class="box-body">
        <form action="userLoginServlet?method=register" method="post"
              name="form">
            <table>
                <tr>
                    <th>学生学号：</th>
                    <!-- 输入用户名称 -->
                    <td><input type="text" name="user" id="user"
                    ></td>
                </tr>
                <tr>
                    <th>姓 名：</th>
                    <td><input type="text" name="name"
                    ></td>
                </tr>
                <tr>
                    <th>密 码：</th>
                    <!-- 输入密码 -->
                    <td><input type="password" name="password"
                    ></td>
                </tr>
                <tr>
                    <th>确认密码：</th>
                    <!-- 重复密码 -->
                    <td><input type="password" name="repwd"
                    ></td>
                </tr>

                <tr>
                    <th>年 级：</th>
                    <td><input type="text" name="grade"
                    ></td>
                </tr>
                <tr>
                    <th>班级：</th>
                    <td><input type="text" name="classes"
                    ></td>
                </tr>
                <tr>
                    <th>电子邮箱：</th>
                    <!-- 输入电子邮箱 -->
                    <td><input type="text" name="email"
                    ></td>
                </tr>
                <tr>
                    <td><br></td>
                </tr>
                <tr>
                    <td colspan="2"><input type="submit" name="submit"
                                           value="注册"> <!-- 注册按钮 --></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>
