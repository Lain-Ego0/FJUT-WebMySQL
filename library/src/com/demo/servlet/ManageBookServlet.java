package com.demo.servlet;

import com.demo.dao.BookDAO;
import com.demo.javabean.Books;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

/**
 * 图书管理Servlet（包含新增、删除、修改、跳转修改页面功能）
 * 配置映射路径，兼容原有AddBookServlet.do访问路径
 */
@WebServlet({"/admin/AddBookServlet.do", "/ManageBookServlet.do"})
public class ManageBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求动作参数
        String action = request.getParameter("action");
        if (action == null) {
            PrintWriter out = response.getWriter();
            out.println("invalid request!");
        } else if (action.equals("addbook")) {
            AddBook(request, response);
        } else if (action.equals("delbook")) {
            DelBook(request, response);
        } else if (action.equals("updatebook")) {
            UpdateBook(request, response);
        } else if (action.equals("update")) {
            Update(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 设置请求编码，解决中文乱码（必须在获取参数前执行）
        req.setCharacterEncoding("utf-8");
        // 2. 设置响应编码，避免前端提示框乱码
        resp.setContentType("text/html;charset=utf-8");
        // 3. 调用doGet方法统一处理业务
        doGet(req, resp);
    }

    /**
     * 跳转图书修改页面
     */
    private void Update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer sid = Integer.parseInt(req.getParameter("sid"));
        BookDAO b_dao = new BookDAO();
        try {
            // 获取包含所有字段的图书对象
            Books book = b_dao.getBookById(sid);
            req.setAttribute("book", book);
            // 转发到修改页面
            req.getRequestDispatcher("update_book.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            // 异常时给出提示并跳转
            PrintWriter out = resp.getWriter();
            out.println("<script>alert('获取图书信息失败！');" +
                    "window.location.href = \"PageServlet.do?method=showBook\";" +
                    "</script>");
        }
    }

    /**
     * 修改图书信息（完善字段、严谨判断、异常处理）
     */
    private void UpdateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        // 获取前端参数
        String sid = req.getParameter("sid");
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String amount = req.getParameter("amount");
        String category = req.getParameter("category");
        String isbn = req.getParameter("isbn");
        String publisher = req.getParameter("publisher");
        String priceStr = req.getParameter("price");

        // 初始化价格
        BigDecimal price = new BigDecimal("0.00");
        BookDAO b_dao = new BookDAO();
        Books book = new Books();

        // 1. 严谨非空判断（排除null和空白字符）
        if (name != null && !name.trim().isEmpty()
                && author != null && !author.trim().isEmpty()
                && amount != null && !amount.trim().isEmpty()
                && category != null && !category.trim().isEmpty()
                && sid != null && !sid.trim().isEmpty()) {

            // 2. 价格转换：捕获非数字格式异常
            try {
                if (priceStr != null && !priceStr.trim().isEmpty()) {
                    price = new BigDecimal(priceStr.trim());
                }
            } catch (NumberFormatException e) {
                out.println("<script>alert('价格格式错误，请输入有效数字！');" +
                        "window.location.href = \"PageServlet.do?method=showBook\";" +
                        "</script>");
                return; // 终止方法执行
            }

            // 3. 给图书对象赋值（去除参数空白字符）
            book.setId(Integer.parseInt(sid.trim()));
            book.setName(name.trim());
            book.setAuthor(author.trim());
            book.setIsbn(isbn);
            book.setPublisher(publisher);
            book.setPrice(price);
            book.setAmount(Integer.parseInt(amount.trim()));
            book.setCategory(category.trim());

            // 4. 调用DAO修改图书
            try {
                if (b_dao.updateBook2(book)) {
                    out.println("<script>alert('修改书籍成功!');" +
                            "window.location.href = \"PageServlet.do?method=showBook\";" +
                            "</script>");
                } else {
                    out.println("<script>alert('修改书籍失败！内容不能为空');" +
                            "window.location.href = \"PageServlet.do?method=showBook\";" +
                            "</script>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<script>alert('修改书籍失败！数据库异常');" +
                        "window.location.href = \"PageServlet.do?method=showBook\";" +
                        "</script>");
            }
        } else {
            out.println("<script>alert('修改书籍失败！必填项不能为空');" +
                    "window.location.href = \"PageServlet.do?method=showBook\";" +
                    "</script>");
        }
    }

    /**
     * 删除图书信息
     */
    private void DelBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        BookDAO b_dao = new BookDAO();

        // 严谨判断图书名称
        if (name != null && !name.trim().isEmpty()) {
            try {
                if (b_dao.delbook(name.trim())) {
                    out.println("<script>alert('删除书籍成功！');" +
                            "window.location.href = \"PageServlet.do?method=showBook\";" +
                            "</script>");
                    return;
                } else {
                    out.println("<script>alert('删除书籍失败！未找到该图书');" +
                            "window.location.href = \"del_book.jsp\";" +
                            "</script>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<script>alert('删除书籍失败！数据库异常');" +
                        "window.location.href = \"del_book.jsp\";" +
                        "</script>");
            }
        } else {
            out.println("<script>alert('删除书籍失败！图书名称不能为空');" +
                    "window.location.href = \"del_book.jsp\";" +
                    "</script>");
        }
    }

    /**
     * 新增图书信息（修复：获取intro参数，完善逻辑）
     */
    private void AddBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        // 获取前端参数（修复：统一参数名，新增intro参数）
        String name = req.getParameter("name");
        String author = req.getParameter("author");
        String amount = req.getParameter("amount");
        String category = req.getParameter("category");
        String isbn = req.getParameter("isbn");
        String publisher = req.getParameter("publisher");
        String priceStr = req.getParameter("price");
        String intro = req.getParameter("intro"); // 从表单获取简介，不再硬编码

        // 处理简介为空的默认值
        if (intro == null || intro.trim().isEmpty()) {
            intro = "暂未介绍";
        }

        // 初始化价格
        BigDecimal price = new BigDecimal("0.00");
        BookDAO b_dao = new BookDAO();
        Books book = null;

        // 1. 严谨非空判断（排除null和空白字符）
        if (name != null && !name.trim().isEmpty()
                && author != null && !author.trim().isEmpty()
                && amount != null && !amount.trim().isEmpty()
                && category != null && !category.trim().isEmpty()) {

            // 2. 价格转换：捕获非数字格式异常
            try {
                if (priceStr != null && !priceStr.trim().isEmpty()) {
                    price = new BigDecimal(priceStr.trim());
                }
            } catch (NumberFormatException e) {
                out.println("<script>alert('价格格式错误，请输入有效数字！');" +
                        "window.location.href = \"reg_book.jsp\";" +
                        "</script>");
                return; // 终止方法执行
            }

            // 3. 给图书对象赋值（去除参数空白字符）
            book = new Books();
            book.setName(name.trim());
            book.setAuthor(author.trim());
            book.setIntro(intro.trim()); // 赋值简介
            book.setIsbn(isbn);
            book.setPublisher(publisher);
            book.setPrice(price);
            book.setAmount(Integer.parseInt(amount.trim()));
            book.setCategory(category.trim());

            // 4. 调用DAO新增图书
            try {
                b_dao.addBook(book); // 调用修复后的addBook方法
                out.println("<script>alert('添加书籍成功!');" +
                        "window.location.href = \"reg_book.jsp\";" +
                        "</script>");
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<script>alert('添加书籍失败！数据库异常：" + e.getMessage() + "');" +
                        "window.location.href = \"reg_book.jsp\";" +
                        "</script>");
            }
        } else {
            out.println("<script>alert('添加书籍失败！必填项（名称/作者/数量/类型）不能为空');" +
                    "window.location.href = \"reg_book.jsp\";" +
                    "</script>");
        }
    }
}