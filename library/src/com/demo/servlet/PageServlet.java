package com.demo.servlet;

import com.demo.dao.BookDAO;
import com.demo.dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.demo.javabean.Books;
import com.demo.javabean.Students;

public class PageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
        String turn = request.getParameter("turn");
        if ("delStudent".equals(method)) {
            doDelStudent(request, response);
        } else if ("showBook".equals(method)) {
            doShowBook(request, response);
        } else if ("showStudent".equals(method)) {
            doShowStudent(request, response);
        }
    }

    private void doShowStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currpage = 1;


        if (request.getParameter("page") != null) {
            currpage = Integer.parseInt(request.getParameter("page"));
        }

        String user = request.getParameter("user");
        String name = request.getParameter("name");

        StudentDAO studao = new StudentDAO();
        HttpSession session = request.getSession();
        ArrayList<Students> list = studao.findAll(currpage,user,name);
        session.setAttribute("list", list);
        int pages;
        int count = studao.countPage();
        System.out.println(count);

        if (count % Students.PAGE_SIZE == 0) {
            pages = count / Students.PAGE_SIZE;
        } else {
            pages = count / Students.PAGE_SIZE + 1;
        }
        StringBuffer sb = new StringBuffer();
        //String turn=request.getParameter("turn");
        request.setAttribute("bar", sb.toString());
        for (int i = 1; i <= pages; i++) {
            if (i == currpage) {
                sb.append('[' + "" + i + "" + ']');
            } else {
                sb.append("<a href='PageServlet.do?method=showStudent&page=" + i + "'>" + i + "</a>");


            }
            sb.append(" ");
        }
        request.setAttribute("bar", sb.toString());
        request.getRequestDispatcher("admin/show1_student.jsp").forward(request, response);
    }

    private void doShowBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currpage = 1;
        if (request.getParameter("page") != null) {
            currpage = Integer.parseInt(request.getParameter("page"));
        }
        String name = request.getParameter("name");
        BookDAO bookdao = new BookDAO();
        ArrayList<Books> list = bookdao.findAll(currpage,name);
        request.setAttribute("list", list);
        int pages;
        int count = bookdao.countPage("1");
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
                sb.append("<a href='PageServlet.do?method=showBook&page=" + i + "'>" + i + "</a>");

            }
            sb.append(" ");
        }
        request.setAttribute("bar", sb.toString());
        request.getRequestDispatcher("admin/show_book.jsp").forward(request, response);
    }

    private void doDelStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currpage = 1;
        if (request.getParameter("page") != null) {
            currpage = Integer.parseInt(request.getParameter("page"));
        }
        StudentDAO studao = new StudentDAO();
        HttpSession session = request.getSession();
        ArrayList<Students> list = studao.findAll(currpage, null, null);
        //System.out.println(list.size());
        session.setAttribute("list", list);
        int pages;
        int count = studao.countPage();
        System.out.println(count);
        //������ҳ��
        if (count % Students.PAGE_SIZE == 0) {
            pages = count / Students.PAGE_SIZE;
        } else {
            pages = count / Students.PAGE_SIZE + 1;
        }
        StringBuffer sb = new StringBuffer();
        String turn = request.getParameter("turn");
        request.setAttribute("bar", sb.toString());
        for (int i = 1; i <= pages; i++) {

            if (i == currpage) {
                sb.append('[' + "" + i + "" + ']');
            } else {
                sb.append("<a href='PageServlet.do?method=delStudent&page=" + i + "'>" + i + "</a>");


            }
            sb.append(" ");
        }
        request.setAttribute("bar", sb.toString());
        request.getRequestDispatcher("admin/del_student.jsp").forward(request, response);
    }

}
