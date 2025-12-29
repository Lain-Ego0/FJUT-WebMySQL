<%@ page import="com.demo.javabean.Books" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.demo.javabean.Borrows" %>
<%@ page import="com.demo.dao.BookDAO" %>
<%@ page import="com.demo.javabean.Students" %>
<%@ page import="com.demo.dao.StudentDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>show Book</title>
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
        td.pagination {
            text-align: center;
            padding: 10px;
            background-color: #f8f9fa;
            border: 1px solid #dee2e6;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <%
        ArrayList<Borrows> borrows = (ArrayList<Borrows>) request.getAttribute("my_borrows");
        Students student01 = (Students)request.getAttribute("my_message");
        if(borrows == null && student01 == null){
            request.setCharacterEncoding("utf-8");
    %>
        <!-- <div class="box-body" id="box-body" style="overflow-y: auto"> -->
            <%
             // ArrayList<Books> books = null;
            ArrayList<Books> books  = (ArrayList<Books>) session.getAttribute("books");
             // if(books == null){
                 // BookDAO b_dao = new BookDAO();

               // books  = (ArrayList<Books>) session.getAttribute("books");
                 // session.setAttribute("books",books);
              //}
              if(books != null && books.size() > 0){
                  String category = null;
                  switch (books.get(0).getCategory()){
                      case "literature":
                          category = "文学书籍";
                          break;
                      case "science":
                          category = "理工书籍";
                          break;
                      case "history":
                          category = "历史书籍";
                          break;
                      case "philo":
                          category = "哲学书籍";
                          break;
                      case "foreign":
                          category = "外语书籍";
                          break;
                      case "political":
                          category = "政治书籍";
                          break;
                      case "program":
                          category = "编程书籍";
                          break;
                      default:
                          category = "未知书籍";
                          break;
                  }
                  for(int i = 0; i < books.size(); i++){
                      if(!books.get(0).getCategory().equals(books.get(i).getCategory())){
                          category = "全部书籍";
                          break;
                      }
                  }
            %>
        <h2><%=category%></h2>
    <form action="userChoiceServlet.do" method="get">
        <input type="text" name="name" placeholder="请输入图书名称">
        <input type="text" name="signal" value="1" hidden>
        <input type="submit" value="查询">
    </form>
        <table>
            <tr>
                <td width="250px"><%="图书名称"%></td>
                <td width="200px"><%="图书作者"%></td>
                <td width="150px"><%="图书类型"%></td>
                <td width="100px"><%="图书数量"%></td>
                <td width="50px"><%="操作"%></td>
            </tr>

            <%
             Students student = (Students)session.getAttribute("student");
             if(books.isEmpty()){
             out.print("error");
             return;
             }
             else
                for (Books book:books){
            %>
            <tr>
                <td><%=book.getName()%></td>
                <td><%=book.getAuthor()%></td>
                <td><%=book.getCategory()%></td>
                <td><%=book.getAmount()%></td>
         <td><a href="${pageContext.request.contextPath}/borrowServlet.do?s_id=<%=student.getId()%>&b_id=<%=book.getId()%>">借书</a></td>

            </tr>
        <%
                }
              }
           }
        %>
         <tr>
    <td align="center" colspan="6" class="pagination">
    <%=request.getAttribute("bar")   %>/页
    </td>
    </tr>
    </div>
</body>
</html>