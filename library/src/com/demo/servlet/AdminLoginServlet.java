package com.demo.servlet;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.demo. dao.AdminDAO;
import com.demo.javabean.*;

public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String method = (String)req.getParameter("method");
    	if(method==null) {
			PrintWriter out = resp.getWriter();
			out.println("invalid request!");
		} else if(method.equals("login")) {
			Login(req, resp);
		}
    }
    //管理员登录
    protected void Login(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out = response.getWriter();
        String a_user =request.getParameter("a_user");
        String a_password = request.getParameter("a_password");
        //HttpSession session = request.getSession();
        AdminDAO a_dao = new AdminDAO();
        if ( a_user .equals("") ||a_password .equals("")) {
        	out.println("<script>" +
        			"alert('登录失败，用户名或密码不能为空!!!');" +
        			"window.location.href = \"login.jsp\";" +
					"</script>");
			return;
		}
        boolean isValid =  a_dao.valid(a_user, a_password);
		if (isValid) {
			HttpSession session = request.getSession();
			session.setAttribute("a_user", a_user);
			out.println("<script>" +
					"alert('登录成功!!!');" +
					"window.location.href = \"admin.jsp\";" +
					"</script>");
			return;
		}
		else {
			out.println("<script>" +
					"alert('登录失败，密码不正确!!!');" +
					"window.location.href = \"login.jsp\";" +
					"</script>");
			return;
		}
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
