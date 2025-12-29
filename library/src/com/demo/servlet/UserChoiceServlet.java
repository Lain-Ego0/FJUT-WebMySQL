package com.demo.servlet;
//importcom.demo.dao.BookDAO;

import com.demo.dao.BookDAO;
import com.demo.javabean.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

public class UserChoiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ȡsession
        HttpSession session = request.getSession();
        //if(session!=null&&session.getAttribute("a_user")!=null){
        String signal = request.getParameter("signal");
        String name = request.getParameter("name");
        //Students student = (Students)session.getAttribute("student");
        int currpage = 1;
        if (request.getParameter("page") != null) {
            currpage = Integer.parseInt(request.getParameter("page"));
        }
        BookDAO b_dao = new BookDAO();
        ArrayList<Books> books;
        try {
            switch (signal) {
                case "1":
                    //books = null;
                    books = b_dao.findAll(currpage, name);
                    if (books.isEmpty()) {
                        System.out.println("error");
                        return;
                    } else {
                        session.setAttribute("books", books);
                    }

                    break;
                default:
                    books = b_dao.getBooksByCategory(signal, currpage);
                    session.setAttribute("books", books);
                    break;
            }
            //session.setAttribute("books",books);
            int pages;
            int count = b_dao.countPage(signal);
            //System.out.println(count);
            if (count % Students.PAGE_SIZE == 0) {
                pages = count / Students.PAGE_SIZE;
            } else {
                pages = count / Students.PAGE_SIZE + 1;
            }
            StringBuffer sb = new StringBuffer();
            for (int i = 1; i <= pages; i++) {
                if (i == currpage) {
                    sb.append('[' + "" + i + "" + ']');
                } else {
                    sb.append("<a href='userChoiceServlet.do?signal=" + signal + "&page=" + i + "'>" + i + "</a>");

                }
                sb.append(" ");
            }
            request.setAttribute("bar", sb.toString());
            //response.sendRedirect("user/showBook.jsp");
            request.getRequestDispatcher("user/showBook.jsp").forward(request, response);
            return;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
