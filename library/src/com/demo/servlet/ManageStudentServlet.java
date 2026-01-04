package com.demo.servlet;

import com.demo.dao.StudentDAO;
import com.demo.dao.BorrowDAO;
import com.demo.javabean.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 修复1：添加@WebServlet映射，匹配表单的AddStudentServlet.do
@WebServlet({"/admin/AddStudentServlet.do", "/ManageStudentServlet.do"})
public class ManageStudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 修复：统一编码，解决中文乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String action = request.getParameter("action");
        if (action == null) {
            PrintWriter out = response.getWriter();
            out.println("invalid request!");
        } else if (action.equals("addstudent")) {
            AddStudent(request, response);
        } else if (action.equals("delstudent")) {
            DelStudent(request, response);
        } else if (action.equals("showstudent")) {
            ShowStudent(request, response);
        }
    }

    private void ShowStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("user");
        StudentDAO studao = new StudentDAO();
        Students student = new Students();
        try {
            student = studao.getStudentByName(user);
            request.setAttribute("student", student);

            // 查询该学生逾期未还图书数量
            BorrowDAO boDao = new BorrowDAO();
            int overdueCount = boDao.countOverdueBySId(student.getId());
            request.setAttribute("overdueCount", overdueCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("show_student.jsp").forward(request, response);
    }

    private void DelStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = resp.getWriter();
        String user = req.getParameter("user");
        StudentDAO s_dao = new StudentDAO();
        if (user != null && !user.trim().isEmpty()) {
            try {
                if (s_dao.delStudentByName(user)) {
                    out.println("<script>alert('删除成功！');" +
                            "window.location.href=\"PageServlet.do?method=showStudent\";" + "</script>");
                    return;
                } else {
                    out.println("<script>alert('删除失败！未找到该学生');" +
                            "window.location.href=\"del_student.jsp\";" + "</script>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.println("<script>alert('删除失败！数据库异常');" +
                        "window.location.href=\"del_student.jsp\";" + "</script>");
            }
        } else {
            out.println("<script>alert('删除失败，学号不能为空！');" +
                    "window.location.href=\"del_student.jsp\";" + "</script>");
        }
    }

    private void AddStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        // 修复2：获取与表单匹配的参数
        String user = req.getParameter("user"); // 学号
        String password = req.getParameter("password"); // 密码
        String relpwd = req.getParameter("relpwd"); // 确认密码
        String name = req.getParameter("name"); // 姓名
        String grade = req.getParameter("grade"); // 年级
        String classes = req.getParameter("classes"); // 班级
        String email = req.getParameter("email"); // 邮箱
        String cardId = req.getParameter("cardId"); // 借书证号
        String unit = req.getParameter("unit"); // 所在单位
        String occupation = req.getParameter("occupation"); // 职业
        // 可选字段：电话、院系（暂存，可根据需求扩展）
        String phone = req.getParameter("phone");
        String department = req.getParameter("department");

        // 修复3：完善非空判断（匹配表单必填项）
        if (user == null || user.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || relpwd == null || relpwd.trim().isEmpty()
                || !password.equals(relpwd)
                || name == null || name.trim().isEmpty()
                || grade == null || grade.trim().isEmpty()
                || classes == null || classes.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || cardId == null || cardId.trim().isEmpty()) {
            out.println("<script>alert('添加失败！必填项（学号/密码/确认密码/姓名/年级/班级/邮箱/借书证号）不能为空，且两次密码需一致');" +
                    "window.location.href = \"add_student.jsp\";" +
                    "</script>");
            return;
        }

        StudentDAO a_dao = new StudentDAO();
        try {
            // 校验学号是否已存在
            boolean isExist = a_dao.isExist(user);
            if (isExist) {
                out.println("<script>alert('此学号已注册！');" +
                        "window.location.href = \"add_student.jsp\";" +
                        "</script>");
                return;
            }

            // 修复4：给Student对象赋值（包含新增字段）
            Students student = new Students();
            student.setUser(user);
            student.setPassword(password);
            student.setName(name);
            student.setGrade(grade);
            student.setClasses(classes);
            student.setEmail(email);
            student.setAmount(0); // 初始借书数为0
            student.setCard_id(cardId); // 借书证号
            student.setUnit(unit == null ? "" : unit); // 所在单位（默认空）
            student.setOccupation(occupation == null ? "" : occupation); // 职业（默认空）

            // 调用DAO添加学生
            if (a_dao.add(student)) {
                out.println("<script>alert('添加成功！');" +
                        "window.location.href = \"add_student.jsp\";" +
                        "</script>");
            } else {
                out.println("<script>alert('添加失败！数据库插入异常');" +
                        "window.location.href = \"add_student.jsp\";" +
                        "</script>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<script>alert('添加失败！" + e.getMessage() + "');" +
                    "window.location.href = \"add_student.jsp\";" +
                    "</script>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        doGet(req, resp);
    }
}