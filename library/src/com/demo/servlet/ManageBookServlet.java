package com.demo.servlet;

import com.demo.dao.BookDAO;
import com.demo.javabean.*;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class ManageBookServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//	HttpSession session=request.getSession();
//		if(session!=null&&session.getAttribute("a_user1")!=null){
//			String action = (String)request.getParameter("action");
//			if(action==null) {
//				PrintWriter out = response.getWriter();
//				out.println("invalid request!");
//			} else if(action.equals("addbook")) {
//				AddBook(request, response);
//			}
//			else if(action.equals("delbook")) {
//				DelBook(request, response);
//			}
//			else if(action.equals("updatebook")) {
//				UpdateBook(request, response);
//			}
//			else if(action.equals("update")) {
//				Update(request, response);
//			}
//		}
//		else {
//			response.sendRedirect("login.jsp");
//		}
		String action = (String)request.getParameter("action");
		if(action==null) {
		PrintWriter out = response.getWriter();
		out.println("invalid request!");
	} else if(action.equals("addbook")) {
		AddBook(request, response);
	}
	else if(action.equals("delbook")) {
		DelBook(request, response);
	}
	else if(action.equals("updatebook")) {
		UpdateBook(request, response);
	}
	else if(action.equals("update")) {
		Update(request, response);
	}
	}

	//更新图书
	private void Update(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		Integer sid = Integer.parseInt(req.getParameter("sid"));
		BookDAO b_dao=new BookDAO();
		String page="";
		try {
			Books book=b_dao.getBookById(sid);
			req.setAttribute("book", book);
			req.getRequestDispatcher("admin/update_book.jsp").forward(req, resp);
		} catch (Exception e) {
			//page="error.jsp";
			e.printStackTrace();
		}
		//        finally{
		//        	page="admin/update_book.jsp";
		//        }
		//        req.getRequestDispatcher(page).forward(req, resp);
	}
	private void UpdateBook(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {//修改图书信息
		PrintWriter out=resp.getWriter();
		String sid=req.getParameter("sid");
		String name=req.getParameter("name");
		String author=req.getParameter("author");
		String amount = req.getParameter("amount");
		String category = req.getParameter("category");
		BookDAO b_dao=new BookDAO();
		Books book=new Books();
		if(name != "" && author != "" && amount != "" && category != ""){
			book.setName(name);
			book.setAuthor(author);
			book.setAmount(Integer.parseInt(amount));
			book.setCategory(category);
			book.setId(Integer.parseInt(sid));
			try {
				if(b_dao.updateBook2(book)){
					out.println("<script>alert('修改书籍成功!');" +
							"window.location.href = \"PageServlet.do?method=showBook\";" +
							"</script>");
					return;
				}
				else
					out.println("<script>alert('修改书籍失败！内容不能为空');" +
							"window.location.href = \"PageServlet.do?method=showBook\";" +
							"</script>");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}else
			out.println("<script>alert('修改书籍失败！');" +
					"window.location.href = \"PageServlet.do?method=showBook\";" +
					"</script>");

	}

	//删除图书
	private void DelBook(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		BookDAO b_dao = new BookDAO();
		if(name!=null){
			try {
				if(b_dao.delbook(name)){
					out.println("<script>alert('删除书籍成功！');" +
							"window.location.href = \"PageServlet.do?method=showBook\";" +
							"</script>");
					return;
				}
				else 
					out.println("<script>alert('删除书籍失败！');" +
							"window.location.href = \"admin/del_book.jsp\";" +
							"</script>");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			out.println("<script>alert('删除书籍失败！');" +
					"window.location.href = \"admin/del_book.jsp\";" +
					"</script>");
		}
	}

	//添加图书
	private void AddBook(HttpServletRequest req, HttpServletResponse resp)throws ServletException,IOException {
		PrintWriter out=resp.getWriter();
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		String amount = req.getParameter("amount");
		String category = req.getParameter("category");
		String intro="暂未介绍";
		BookDAO b_dao = new BookDAO();
		Books book = null;
		if(name != "" && author != "" && amount != "" && category != ""){
			book = new Books();
			book.setName(name);
			book.setAuthor(author);
			book.setIntro("暂未介绍");
			book.setAmount(Integer.parseInt(amount));
			book.setCategory(category);
			//PrintWriter out = resp.getWriter();
			try {
				b_dao.addBook(name, author, intro, amount, category);
				out.println("<script>alert('添加书籍成功!');" +
						"window.location.href = \"admin/reg_book.jsp\";" +
						"</script>");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			out.println("<script>alert('添加书籍失败!！其他原因');" +
					"window.location.href = \"admin/reg_book.jsp\";" +
					"</script>");
		}

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.setCharacterEncoding("utf-8");
		doGet(req,resp);

	}
}
