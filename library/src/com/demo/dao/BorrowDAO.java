package com.demo.dao;

import com.demo.javabean.Borrows;
import com.demo.javabean.DBAccess;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class BorrowDAO  {
    private Borrows borrow = null;

    /**
     * 查询所有借阅记录
     * @return 借阅记录列表
     * @throws Exception 异常信息
     */
    public ArrayList<Borrows> getAllBorrows() throws Exception{
        DBAccess db = new DBAccess();
        java.sql.PreparedStatement pre = null;
        String sql = "SELECT * FROM borrows";
        ArrayList<Borrows> borrows = new ArrayList<Borrows>();
        try {
            if(db.createConn()){
                pre = db.getConn().prepareStatement(sql);
                db.setRs(pre.executeQuery());
                while(db.getRs().next()) {
                    borrow = this.assemble(db.getRs());
                    borrows.add(borrow);
                }
            }
        } finally {
            db.closeRs();
            db.closeStm();
            if (pre != null) {
                pre.close();
            }
            db.closeConn();
        }
        return borrows;
    }

    /**
     * 根据学生ID查询借阅记录
     * @param s_id 学生ID
     * @return 借阅记录列表
     * @throws Exception 异常信息
     */
    public ArrayList<Borrows> getBorrowsBySId(int s_id) throws Exception{
        ArrayList<Borrows> borrows = new ArrayList<Borrows>();
        DBAccess db = new DBAccess();
        java.sql.PreparedStatement pre = null;
        String sql = "SELECT * FROM borrows WHERE s_id = ?";
        try {
            if(db.createConn()){
                pre = db.getConn().prepareStatement(sql);
                pre.setInt(1, s_id);
                db.setRs(pre.executeQuery());
                while (db.getRs().next()){
                    borrow = this.assemble(db.getRs());
                    borrows.add(borrow);
                }
            }
        } finally {
            db.closeRs();
            db.closeStm();
            if (pre != null) {
                pre.close();
            }
            db.closeConn();
        }
        return borrows;
    }

    /**
     * 新增借阅记录（核心修复：删除日期字段显式传值，避免覆盖数据库默认值/触发器）
     * @param borrow 借阅对象
     * @return 新增是否成功
     * @throws Exception 异常信息
     */
    public boolean addBorrows(Borrows borrow) throws Exception{
        boolean flag = false;
        DBAccess db = new DBAccess();
        java.sql.PreparedStatement pre = null;
        // 关键修改：SQL仅插入非日期字段，让数据库/触发器自动填充borrow_date和due_date
        String sql = "INSERT INTO borrows(s_id,b_id,card_id,amount) VALUES(?,?,?,?)";
        try {
            if(db.createConn()){
                pre = db.getConn().prepareStatement(sql);
                pre.setInt(1,borrow.getS_id());
                pre.setInt(2,borrow.getB_id());
                pre.setString(3, borrow.getCard_id());
                pre.setInt(4,borrow.getAmount());
                // 不再处理日期字段，彻底避免显式传Null覆盖默认值
                if(pre.executeUpdate() > 0)  flag = true;
            }
        } finally {
            db.closeRs();
            if (pre != null) {
                pre.close();
            }
            db.closeConn();
        }
        return flag;
    }

    /**
     * 删除借阅记录
     * @param borrow 借阅对象
     * @return 删除是否成功
     * @throws Exception 异常信息
     */
    public boolean deleteBorrow(Borrows borrow) throws Exception{
        boolean flag = false;
        DBAccess db = new DBAccess();
        java.sql.PreparedStatement pre = null;
        String sql = "DELETE FROM borrows WHERE s_id = ? AND b_id = ? AND amount = ?";
        try {
            if(db.createConn()){
                pre = db.getConn().prepareStatement(sql);
                pre.setInt(1,borrow.getS_id());
                pre.setInt(2,borrow.getB_id());
                pre.setInt(3,borrow.getAmount());
                if(pre.executeUpdate() > 0) flag = true;
            }
        } finally {
            db.closeRs();
            if (pre != null) {
                pre.close();
            }
            db.closeConn();
        }
        return flag;
    }

    /**
     * 根据学生ID和图书ID查询借阅记录
     * @param s_id 学生ID
     * @param b_id 图书ID
     * @return 借阅对象
     * @throws Exception 异常信息
     */
    public Borrows getBorrowById(int s_id, int b_id) throws Exception{
        DBAccess db = new DBAccess();
        java.sql.PreparedStatement pre = null;
        String sql = "SELECT * FROM borrows WHERE s_id = ? AND b_id = ?";
        try {
            if(db.createConn()){
                pre = db.getConn().prepareStatement(sql);
                pre.setInt(1,s_id);
                pre.setInt(2,b_id);
                db.setRs(pre.executeQuery());
                if(db.getRs().next()) {
                    borrow = this.assemble(db.getRs());
                }
            }
        } finally {
            db.closeRs();
            db.closeStm();
            if (pre != null) {
                pre.close();
            }
            db.closeConn();
        }
        return borrow;
    }

    /**
     * 更新借阅记录（补充card_id和日期字段）
     * @param borrow 借阅对象
     * @return 更新是否成功
     * @throws Exception 异常信息
     */
    public boolean updateBorrow(Borrows borrow) throws  Exception{
        boolean flag = false;
        DBAccess db = new DBAccess();
        java.sql.PreparedStatement pre = null;
        String sql = "UPDATE borrows SET amount = ?, card_id = ?, borrow_date = ?, due_date = ? WHERE s_id = ? AND b_id = ?";
        try {
            if(db.createConn()){
                pre = db.getConn().prepareStatement(sql);
                pre.setInt(1,borrow.getAmount());
                pre.setString(2, borrow.getCard_id());
                // 日期字段使用Timestamp赋值（仅更新时使用，不影响新增逻辑）
                if (borrow.getBorrow_date() != null) {
                    pre.setTimestamp(3, new Timestamp(borrow.getBorrow_date().getTime()));
                } else {
                    pre.setNull(3, java.sql.Types.TIMESTAMP);
                }
                if (borrow.getDue_date() != null) {
                    pre.setTimestamp(4, new Timestamp(borrow.getDue_date().getTime()));
                } else {
                    pre.setNull(4, java.sql.Types.TIMESTAMP);
                }
                pre.setInt(5,borrow.getS_id());
                pre.setInt(6,borrow.getB_id());
                if(pre.executeUpdate() > 0) flag = true;
            }
        } finally {
            db.closeRs();
            db.closeStm();
            if (pre != null) {
                pre.close();
            }
            db.closeConn();
        }
        return flag;
    }

    /**
     * 结果集映射为借阅对象（适配DATETIME类型，正常读取日期）
     * @param rs 结果集
     * @return 借阅对象
     * @throws Exception 异常信息
     */
    public Borrows assemble(ResultSet rs) throws Exception{
        borrow = new Borrows();
        borrow.setS_id(rs.getInt("s_id"));
        borrow.setB_id(rs.getInt("b_id"));
        borrow.setAmount(rs.getInt("amount"));
        borrow.setCard_id(rs.getString("card_id"));
        // 用Timestamp读取DATETIME字段，确保年月日时分秒完整
        Timestamp borrowTimestamp = rs.getTimestamp("borrow_date");
        if (borrowTimestamp != null) {
            borrow.setBorrow_date(new Date(borrowTimestamp.getTime()));
        }
        Timestamp dueTimestamp = rs.getTimestamp("due_date");
        if (dueTimestamp != null) {
            borrow.setDue_date(new Date(dueTimestamp.getTime()));
        }
        Timestamp returnTimestamp = rs.getTimestamp("return_date");
        if (returnTimestamp != null) {
            borrow.setReturn_date(new Date(returnTimestamp.getTime()));
        }
        return borrow;
    }

    /**
     * 根据借书证号查询借阅记录
     * @param cardId 借书证号
     * @return 借阅记录列表
     * @throws Exception 异常信息
     */
    public ArrayList<Borrows> findBorrowsByCardId(String cardId) throws Exception {
        ArrayList<Borrows> borrowList = new ArrayList<Borrows>();
        DBAccess db = new DBAccess();
        java.sql.PreparedStatement pre = null;
        String sql = "SELECT * FROM borrows WHERE card_id = ?";
        try {
            if (db.createConn()) {
                pre = db.getConn().prepareStatement(sql);
                pre.setString(1, cardId);
                db.setRs(pre.executeQuery());
                while (db.getRs().next()) {
                    borrow = this.assemble(db.getRs());
                    borrowList.add(borrow);
                }
            }
        } finally {
            db.closeRs();
            db.closeStm();
            if (pre != null) {
                pre.close();
            }
            db.closeConn();
        }
        return borrowList;
    }

    // 新增：统计指定学生逾期未还图书数量
    public int countOverdueBySId(int s_id) throws Exception {
        int overdueCount = 0;
        DBAccess db = new DBAccess();
        java.sql.PreparedStatement pre = null;
        // SQL逻辑：未还书(return_date is null) + 应还日期 < 当前日期
        String sql = "SELECT COUNT(*) FROM borrows WHERE s_id = ? AND return_date IS NULL AND due_date < CURDATE()";
        if (db.createConn()) {
            pre = db.getConn().prepareStatement(sql);
            pre.setInt(1, s_id);
            db.setRs(pre.executeQuery());
            if (db.getRs().next()) {
                overdueCount = db.getRs().getInt(1);
            }
        }
        // 关闭资源
        db.closeRs();
        if (pre != null) pre.close();
        db.closeConn();
        return overdueCount;
    }
}