package com.demo.dao;

import com.demo.javabean.*;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.demo.javabean.DBAccess;

public class BorrowDAO  {
    private Borrows borrow = null;
    public ArrayList<Borrows> getAllBorrows() throws Exception{
    DBAccess db=new DBAccess();
    java.sql.PreparedStatement pre=null;
       String sql = "SELECT * FROM borrows";
        ArrayList<Borrows> borrows = new ArrayList<Borrows>();
    if(db.createConn()){
	pre=db.getConn().prepareStatement(sql);
	db.setRs(pre.executeQuery());
        while(db.getRs().next()) {
            borrow = this.assemble(db.getRs());
            borrows.add(borrow);
        }
}
        db.closeRs();
        db.closeStm();
        db.closeConn();
        return borrows;
    }

    public ArrayList<Borrows> getBorrowsBySId(int s_id) throws Exception{
        ArrayList<Borrows> borrows = new ArrayList<Borrows>();
        DBAccess db=new DBAccess();
        java.sql.PreparedStatement pre=null;
       String sql = "SELECT * FROM borrows WHERE s_id = ?";
       if(db.createConn()){
    	   pre=db.getConn().prepareStatement(sql);
    	   pre.setInt(1, s_id);
    	   db.setRs(pre.executeQuery());
        while (db.getRs().next()){
            borrow = this.assemble(db.getRs());
            borrows.add(borrow);
        }
       }
       db.closeRs();
       db.closeStm();
       pre.close();
       db.closeConn();
        return borrows;
    }

    public boolean addBorrows(Borrows borrow) throws Exception{
        boolean flag = false;
        DBAccess db=new DBAccess();
        java.sql.PreparedStatement pre=null;
        String sql = "INSERT INTO borrows VALUES(?,?,?)";
        if(db.createConn()){
        pre=db.getConn().prepareStatement(sql);
        pre.setInt(1,borrow.getS_id());
        pre.setInt(2,borrow.getB_id());
        pre.setInt(3,borrow.getAmount());
        if(pre.executeUpdate() > 0)  flag = true;
        }
        db.closeRs();
        pre.close();
        db.closeConn();
        return flag;
    }

    public boolean deleteBorrow(Borrows borrow) throws Exception{
        boolean flag = false;
        DBAccess db=new DBAccess();
        java.sql.PreparedStatement pre=null;
        String sql = "DELETE FROM borrows WHERE s_id = ? AND b_id = ? AND amount = ?";
        if(db.createConn()){
        pre=db.getConn().prepareStatement(sql);
        pre.setInt(1,borrow.getS_id());
        pre.setInt(2,borrow.getB_id());
        pre.setInt(3,borrow.getAmount());
        if(pre.executeUpdate() > 0) flag = true;
        }
        return flag;
    }

    public Borrows getBorrowById(int s_id, int b_id) throws Exception{
    	DBAccess db=new DBAccess();
        String sql = "SELECT * FROM borrows WHERE s_id = ? AND b_id = ?";
        java.sql.PreparedStatement pre=null;
        if(db.createConn()){
        pre=db.getConn().prepareStatement(sql);
        pre.setInt(1,s_id);
        pre.setInt(2,b_id);
        db.setRs(pre.executeQuery());
        if(db.getRs().next()) borrow = this.assemble(db.getRs());
        }
        db.closeRs();
        db.closeStm();
        pre.close();
        db.closeConn();
        return borrow;
    }

   public boolean updateBorrow(Borrows borrow) throws  Exception{
        boolean flag = false;
        DBAccess db=new DBAccess();
        String sql = "UPDATE borrows SET amount = ? WHERE s_id = ? AND b_id = ?";
        java.sql.PreparedStatement pre=null;
        if(db.createConn()){
        pre=db.getConn().prepareStatement(sql);
        pre.setInt(1,borrow.getAmount());
        pre.setInt(2,borrow.getS_id());
        pre.setInt(3,borrow.getB_id());
        if(pre.executeUpdate() > 0) flag = true;
        }
        db.closeRs();
        db.closeStm();
        pre.close();
        db.closeConn();
        return flag;
    }

    public Borrows assemble(ResultSet rs) throws Exception{
        borrow = new Borrows();
        borrow.setS_id(rs.getInt("s_id"));
        borrow.setB_id(rs.getInt("b_id"));
        borrow.setAmount(rs.getInt("amount"));
        return borrow;
    }
}
