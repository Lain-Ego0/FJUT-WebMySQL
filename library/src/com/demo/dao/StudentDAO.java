package com.demo.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.demo.javabean.*;

public class StudentDAO {
    DBAccess db;

    public boolean valid(String username, String password) {
        boolean isValid = false;
        db = new DBAccess();
        if(db.createConn()) {
            String sql="select * from students where user= ? and password= ?";
            try {
                db.pre=db.getConn().prepareStatement(sql);
                db.pre.setString(1, username);
                db.pre.setString(2, password);
                db.setRs(db.pre.executeQuery());
                if(db.getRs().next()){
                    isValid=true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                db.closeRs();
                if(db.pre!=null)
                    try {
                        db.pre.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                db.closeConn();
            }
        }
        return isValid ;
    }

    public boolean isExist(String username) {
        boolean isExist = false;
        db = new DBAccess();
        if(db.createConn()) {
            String sql="select * from students where user=?";
            try {
                db.pre=db.getConn().prepareStatement(sql);
                db.pre.setString(1, username);
                db.setRs(db.pre.executeQuery());
                if(db.getRs().next()){
                    isExist=true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                db.closeRs();
                if(db.pre!=null){
                    try {
                        db.pre.close();
                    } catch (Exception e2) {}
                    db.closeConn();
                }
            }
        }
        return isExist;
    }

    // 修复：移除自增id的插入（若students表id是自增主键）
    public boolean add(Students student) throws Exception{
        boolean flag = false;
        db=new DBAccess();
        // 修复1：SQL移除id字段（自增主键无需手动插入）
        String sql = "INSERT INTO students(user,password,name,grade,classes,email,amount,card_id,unit,occupation) VALUES(?,?,?,?,?,?,?,?,?,?)";
        if(db.createConn()){
            db.pre=db.getConn().prepareStatement(sql);
            // 修复2：调整参数索引（移除id的setInt）
            db.pre.setString(1,student.getUser());
            db.pre.setString(2,student.getPassword());
            db.pre.setString(3,student.getName());
            db.pre.setString(4,student.getGrade());
            db.pre.setString(5,student.getClasses());
            db.pre.setString(6,student.getEmail());
            db.pre.setInt(7,student.getAmount());
            db.pre.setString(8, student.getCard_id());
            db.pre.setString(9, student.getUnit());
            db.pre.setString(10, student.getOccupation());

            if(db.pre.executeUpdate() > 0) flag = true;
        }
        // 修复3：完善资源关闭
        db.closeRs();
        db.closeStm();
        if(db.pre != null) db.pre.close();
        db.closeConn();
        return flag;
    }

    public void del(String usename){
        DBAccess db = new DBAccess();
        if(db.createConn()) {
            String sql = "delete from students where user = '"+usename+"'";
            db.update(sql);
            db.closeStm();
            db.closeConn();
        }
    }

    public ArrayList<Students>getAllStudents() throws Exception{
        DBAccess db = new DBAccess();
        ArrayList<Students> students = new ArrayList<Students>();
        String sql = "SELECT * FROM students";
        if(db.createConn()){
            db.query(sql);
            while(db.getRs().next()){
                Students student = this.assemble(db.getRs());
                students.add(student);
            }
            db.closeStm();
            db.closeRs();
            db.closeConn();
        }
        return students;
    }

    public Students assemble(ResultSet rs) throws Exception{
        Students student = new Students();
        student.setId(rs.getInt("id"));
        student.setUser(rs.getString("user"));
        student.setPassword(rs.getString("password"));
        student.setName(rs.getString("name"));
        student.setGrade(rs.getString("grade"));
        student.setClasses(rs.getString("classes"));
        student.setEmail(rs.getString("email"));
        student.setAmount(rs.getInt("amount"));
        student.setCard_id(rs.getString("card_id"));
        student.setUnit(rs.getString("unit"));
        student.setOccupation(rs.getString("occupation"));
        return student;
    }

    public boolean delStudentByName(String user) throws Exception{
        DBAccess db=new DBAccess();
        boolean flag = false;
        String sql="DELETE FROM students WHERE user = ?";
        if(db.createConn()){
            db.pre=db.getConn().prepareStatement(sql);
            db.pre.setString(1, user);
            if(db.pre.executeUpdate()>0)flag=true;
        }
        db.closeConn();
        db.closeStm();
        if(db.pre != null) db.pre.close();
        db.closeRs();
        return flag;
    }

    public Students getStudentById(int id) throws Exception{
        DBAccess db=new DBAccess();
        Students student = null;
        String sql = "SELECT * FROM students WHERE id = ?";
        java.sql.PreparedStatement pre=null;
        if(db.createConn()){
            pre=db.getConn().prepareStatement(sql);
            pre.setInt(1, id);
            db.setRs(pre.executeQuery());
            if (db.getRs().next()) {
                student = this.assemble(db.getRs());
            }
        }
        db.closeRs();
        db.closeStm();
        if(pre != null) pre.close();
        db.closeConn();
        return student;
    }

    public boolean updateStudent(Students student) throws Exception{
        boolean flag = false;
        DBAccess db=new DBAccess();
        String sql = "update students set amount = ? where id = ?";
        java.sql.PreparedStatement pre=null;
        if(db.createConn()){
            pre=db.getConn().prepareStatement(sql);
            pre.setInt(1,student.getAmount());
            pre.setInt(2,student.getId());
            if(pre.executeUpdate() > 0) flag = true;
        }
        db.closeRs();
        if(pre != null) pre.close();
        db.closeStm();
        db.closeConn();
        return flag;
    }

    public Students getStudentByName(String name) throws Exception{
        DBAccess  db=new DBAccess();
        Students student = null;
        String sql = "SELECT * FROM students WHERE user = ?";
        java.sql.PreparedStatement pre=null;
        if(db.createConn()){
            pre=db.getConn().prepareStatement(sql);
            pre.setString(1, name);
            db.setRs(pre.executeQuery());
            if(db.getRs().next()){
                student = this.assemble(db.getRs());
            }
        }
        db.closeRs();
        db.closeStm();
        if(pre != null) pre.close();
        db.closeConn();
        return student;
    }

    public ArrayList<Students> findAll(Integer page, String user, String name){
        DBAccess  db=new DBAccess();
        ArrayList<Students> list=new ArrayList<Students>();
        try {
            if(db.createConn()){
                StringBuilder sql = new StringBuilder("select * from students where 1=1");
                if (user != null && !user.isEmpty()) {
                    sql.append(" and user = ?");
                }
                if (name != null && !name.isEmpty()) {
                    sql.append(" and name like ?");
                }
                sql.append(" limit ?,?");

                db.pre=db.getConn().prepareStatement(sql.toString());
                int index = 1;
                if (user != null && !user.isEmpty()) {
                    db.pre.setString(index++, user);
                }
                if (name != null && !name.isEmpty()) {
                    db.pre.setString(index++, "%" + name + "%");
                }
                db.pre.setInt(index++, (page-1)*Students.PAGE_SIZE);
                db.pre.setInt(index, Students.PAGE_SIZE);

                db.setRs(db.pre.executeQuery());
                while(db.getRs().next()){
                    Students stu=new Students();
                    stu.setId(db.getRs().getInt("id"));
                    stu.setUser(db.getRs().getString("user"));
                    stu.setPassword(db.getRs().getString("password"));
                    stu.setName(db.getRs().getString("name"));
                    stu.setGrade(db.getRs().getString("grade"));
                    stu.setClasses(db.getRs().getString("classes"));
                    stu.setEmail(db.getRs().getString("email"));
                    stu.setAmount(db.getRs().getInt("amount"));
                    stu.setCard_id(db.getRs().getString("card_id"));
                    stu.setUnit(db.getRs().getString("unit"));
                    stu.setOccupation(db.getRs().getString("occupation"));
                    list.add(stu);
                }
                db.closeRs();
                db.pre.close();
                db.closeConn();
            }
        } catch( SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int countPage(){
        DBAccess  db=new DBAccess();
        int count=0;
        try {
            if(db.createConn()){
                String sql="select count(*) from students";
                db.pre=db.getConn().prepareStatement(sql);
                db.setRs(db.pre.executeQuery());
                if(db.getRs().next()){
                    count=db.getRs().getInt(1);
                }
            }
        } catch( SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeRs();
            try {
                if(db.pre!=null){
                    db.pre.close();
                }
            } catch (Exception e2) {}
            db.closeConn();
        }
        return count;
    }

    public Students findByCardId(String cardId) throws Exception {
        Students student = null;
        DBAccess db = new DBAccess();
        String sql = "SELECT * FROM students WHERE card_id = ?";
        java.sql.PreparedStatement pre = null;
        if (db.createConn()) {
            pre = db.getConn().prepareStatement(sql);
            pre.setString(1, cardId);
            db.setRs(pre.executeQuery());
            if (db.getRs().next()) {
                student = this.assemble(db.getRs());
            }
        }
        db.closeRs();
        db.closeStm();
        if (pre != null) {
            pre.close();
        }
        db.closeConn();
        return student;
    }
}