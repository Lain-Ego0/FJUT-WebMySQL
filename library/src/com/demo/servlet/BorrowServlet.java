package com.demo.servlet;

import com.demo.dao.BookDAO;
import com.demo.dao.BorrowDAO;
import com.demo.dao.StudentDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import com.demo.javabean.Books;
import com.demo.javabean.Borrows;
import com.demo.javabean.Students;

public class BorrowServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int s_id = Integer.parseInt(request.getParameter("s_id"));
        int b_id = Integer.parseInt(request.getParameter("b_id"));

        StudentDAO s_dao = new StudentDAO();
        BookDAO b_dao = new BookDAO();
        BorrowDAO bo_dao = new BorrowDAO();
        Students student = null;
        Books book = null;
        Borrows borrow = null;
        // 声明借书证号变量
        String card_id = null;
        try{
            student = s_dao.getStudentById(s_id);
            book = b_dao.getBookById(b_id);
            // 直接从学生对象中读取借书证号（不再从前端获取）
            if (student != null) {
                card_id = student.getCard_id();
            }

            if(student != null && book != null){
                // 修正：借阅上限从10本改为8本（适配SQL表的CHECK约束 amount <= 8）
                if(student.getAmount() >= 8){
                    out.println("<script>alert('您的借阅图书已达上限（最多8本），暂无法进行借阅!');" +
                            "window.location.href = \"user/showBook.jsp\";" +
                            "</script>");
                    return;
                }
                if(book.getAmount() <= 0){
                    out.println("<script>alert('此图书数量已经为0，请尝试借阅其它书籍!');" +
                            "window.location.href = \"user/showBook.jsp\";" +
                            "</script>");
                    return;
                }
                student.setAmount(student.getAmount() + 1);
                book.setAmount(book.getAmount() - 1);
                s_dao.updateStudent(student);
                b_dao.updateBook(book);
                borrow = bo_dao.getBorrowById(s_id,b_id);
                if(borrow == null){
                    borrow = new Borrows();
                    borrow.setS_id(s_id);
                    borrow.setB_id(b_id);
                    borrow.setAmount(1);
                    // 新增：设置借书证号（关联Borrows新增字段，直接使用读取到的学生证号）
                    borrow.setCard_id(card_id);
                    // 日期字段无需手动设置（数据库已配置默认值：borrow_date默认当前时间，due_date默认30天后）
                    // borrow.setBorrow_date(null); // 可省略，数据库自动填充
                    // borrow.setDue_date(null); // 可省略，数据库自动填充
                    // borrow.setReturn_date(null); // 未还书，默认null
                    bo_dao.addBorrows(borrow);
                }else{
                    borrow.setAmount(borrow.getAmount() + 1);
                    // 新增：更新已有借阅记录的借书证号（若原有记录未填充，可选增强，直接使用读取到的学生证号）
                    borrow.setCard_id(card_id);
                    bo_dao.updateBorrow(borrow);
                }
                HttpSession session = request.getSession();

                @SuppressWarnings("unchecked")
                ArrayList<Books> r_books = (ArrayList<Books>) session.getAttribute("books");
                boolean flag = false;
                for(int i = 0; i < r_books.size(); i++){
                    if(!r_books.get(0).getCategory().equals(r_books.get(i).getCategory())){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    session.setAttribute("books",b_dao.getAllBooks());
                }else {
                    session.setAttribute("books",b_dao.getBooksByCategory(book.getCategory()));
                }
                out.println("<script>" +
                        "alert('图书借阅成功!!!');" +
                        "window.location.href = \"userChoiceServlet.do?signal=1\";" +
                        "</script>");
            }else{
                out.println("<script>alert('无效的图书或用户，无法进行借阅!');" +
                        "window.location.href = \"userChoiceServlet.do?signal=1\";" +
                        "</script>");
            }
        }catch (Exception e){
            System.out.println("出现异常，学生进行图书借阅失败!");
            System.out.println("!!!"+s_id+" "+b_id);
            // 新增：异常时提示借书失败
            out.println("<script>alert('借阅异常，请稍后再试!');" +
                    "window.location.href = \"user/showBook.jsp\";" +
                    "</script>");
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}