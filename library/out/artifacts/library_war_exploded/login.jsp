<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="css/login.css">

</head>
<body>
	<div class="login-wrap">
		<%-- 登录界面的div块--%>
		<div class="login-html">
			<h2>图书管理系统</h2>
			<%--二级标题, 登录信息--%>
			<%--分为两种登录方式, 学生登录和管理员身份登录--%>
			<input id="tab-1" type="radio" name="tab" class="sign-in" checked>
			<label for="tab-1" class="tab">学生</label> <input id="tab-2"
				type="radio" name="tab" class="sign-up"> <label for="tab-2"
				class="tab">管理员</label>
			<div class="login-form">
				<div class="sign-in-htm">
					<form action="userLoginServlet?method=login" method="post"
						name="form_user">
						<%--学生学号登录的表单，提交给userLoginServlet进行处理--%>
						<div class="group">
							<label class="label">学号 <a href="reg.jsp" class="reg">没有账号?立即注册</a></label>

							<input name="user" type="text" class="input" placeholder="输入您的学号"
								autocomplete="off" >
						</div>
						<div class="group">
							<label class="label">密码</label> <input name="password"
								type="password" class="input" placeholder="输入您的登录密码"
								autocomplete="off">
						</div>
						<div class="group">
							<input type="checkbox" name="remember" value="1"
								checked="checked">记住密码
						</div>
						<div class="group">

							<input type="submit" class="button" value="登录">
						</div>
					</form>
				</div>
				<div class="sign-up-htm">
					<form action="adminLoginServlet?method=login" method="post"
						name="form_admin">
						<%--管理员登录的表单，提交给AdminLoginServlet进行处理--%>
						<div class="group">
							<label class="label">管理员账号</label> <input name="a_user"
								type="text" class="input" placeholder="输入管理员账号"
								autocomplete="off">
						</div>
						<div class="group">
							<label class="label">管理员密码</label> <input name="a_password"
								type="password" class="input" placeholder="输入您的管理员密码"
								autocomplete="off">
						</div>
						<div class="group">
							<p>暂不支持记住密码和注册</p>
						</div>
						<div class="group">
							<input type="submit" class="button" value="登录" />
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>
