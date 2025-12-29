
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reg Book</title>
    <link rel="stylesheet" href="../css/reg.css">
</head>
<body style="background-image: none">
    <div class="box-body" id="box-body" style="overflow-y: auto;">
        <form action="../manageBookServlet.do?action=addbook" method="post" name="form">
            <table>
                <tr>
                    <th>图书名称: </th>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <th>图书作者: </th>
                    <td><input type="text" name="author" ></td>
                </tr>
                <tr>
                    <th>图书数量: </th>
                    <td><input type="text" name="amount"  ></td>
                </tr>
                <tr>
                    <th>图书类型: </th>
                    <td><input type="text" name="category"  ></td>
                </tr>
            </table>
            <br>
            <input type="submit" onclick="check_data_book()" value="提交">
        </form>
    </div>
</body>
</html>
