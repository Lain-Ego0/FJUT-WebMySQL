<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加书籍</title>
    <link rel="stylesheet" href="../css/head02.css"> <!-- 保留原有样式 -->
</head>
<body>
<div class="book-form"> <!-- 保留原有容器结构 -->
    <h3>添加书籍信息</h3>
    <!-- 修复：补充action=addbook参数，统一参数名与Servlet匹配 -->
    <form action="AddBookServlet.do?action=addbook" method="post">
        <div class="form-row">
            <label>图书名称：</label>
            <input type="text" name="name" required> <!-- 参数名改为name -->
        </div>
        <div class="form-row">
            <label>图书作者：</label>
            <input type="text" name="author" required> <!-- 参数名改为author -->
        </div>
        <div class="form-row">
            <label>图书简介：</label>
            <textarea name="intro" class="form-control"></textarea> <!-- 参数名保持intro -->
        </div>
        <div class="form-row">
            <label>图书数量：</label>
            <input type="number" name="amount" min="1" required> <!-- 参数名改为amount -->
        </div>
        <div class="form-row">
            <label>图书类型：</label>
            <input type="text" name="category" required> <!-- 参数名改为category -->
        </div>
        <div class="form-row">
            <label>ISBN：</label>
            <input type="text" name="isbn" required> <!-- 参数名保持isbn -->
        </div>
        <div class="form-row">
            <label>出版社：</label>
            <input type="text" name="publisher" required> <!-- 参数名保持publisher -->
        </div>
        <div class="form-row">
            <label>定价：</label>
            <input type="number" name="price" step="0.01" min="0" required> <!-- 参数名保持price -->
        </div>
        <div class="btn-group"> <!-- 保留原有按钮组样式 -->
            <input type="submit" value="提交">
            <input type="reset" value="重置">
        </div>
    </form>
</div>
</body>
</html>