<%@ page import="com.demo.dao.StudentDAO" %>
<%@ page import="com.demo.javabean.Students" %>
<%@ page import="com.demo.dao.BorrowDAO" %>
<%@ page import="com.demo.javabean.Borrows" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.demo.dao.BookDAO" %>
<%@ page import="java.text.SimpleDateFormat" %> <!-- 新增：导入日期格式化类 -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Borrow Books</title>
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
    // 新增：初始化日期格式化工具，统一日期显示格式
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    BorrowDAO bo_dao = new BorrowDAO();
    StudentDAO s_dao = new StudentDAO();
    ArrayList<Borrows> borrows = null;
    Students student = (Students)session.getAttribute("student");
    borrows = bo_dao.getBorrowsBySId(student.getId());
    if(borrows != null && borrows.size() > 0){
        BookDAO b_dao = new BookDAO();
%>
<div id="my_borrow" name="my_borrow" style="height: 80%;overflow-y: auto;" >
    <h2><%="我的已借书籍"%></h2>
    <table>
        <tr>
            <td width="250px"><%="图书名称"%></td>
            <td width="200px"><%="图书作者"%></td>
            <td width="150px"><%="图书类型"%></td>
            <td width="100px"><%="图书数量"%></td>
            <td width="150px"><%="借阅时间"%></td> <!-- 新增：借阅时间列 -->
            <td width="150px"><%="应还时间"%></td> <!-- 新增：应还时间列 -->
            <td width="50px"><%="操作"%></td>
        </tr>
        <%
            for(Borrows borrow: borrows){
                // 处理日期空值，避免页面显示null
                String borrowDate = borrow.getBorrow_date() == null ? "未记录" : sdf.format(borrow.getBorrow_date());
                String dueDate = borrow.getDue_date() == null ? "未设置" : sdf.format(borrow.getDue_date());
        %>
        <tr>
            <td><%=b_dao.getBookById(borrow.getB_id()).getName()%></td>
            <td><%=b_dao.getBookById(borrow.getB_id()).getAuthor()%></td>
            <td><%=b_dao.getBookById(borrow.getB_id()).getCategory()%></td>
            <td><%=borrow.getAmount()%></td>
            <td><%=borrowDate%></td> <!-- 显示格式化后的借阅时间 -->
            <td><%=dueDate%></td>   <!-- 显示格式化后的应还时间 -->
            <td><a href="../returnServlet?s_id=<%=student.getId()%>&b_id=<%=borrow.getB_id()%>">还书</a></td>
        </tr>
        <%
            }
        %>
    </table>
    <%
        }
    %>
</div>
</body>
</html>