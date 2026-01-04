package com.demo.javabean;

// 新增：导入Date类，用于映射数据库的datetime类型日期字段
import java.util.Date;

public class Borrows {

    private int s_id;
    private int b_id;
    private int amount;
    // 新增：对应SQL表的4个字段（借书证号、借书日期、应还日期、实际还书日期）
    private String card_id;
    private Date borrow_date;
    private Date due_date;
    private Date return_date;

    public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }

    public int getB_id() {
        return b_id;
    }

    public void setB_id(int b_id) {
        this.b_id = b_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    // 新增：card_id（借书证号）的getter/setter方法，格式与原有保持一致
    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    // 新增：borrow_date（借书日期）的getter/setter方法
    public Date getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(Date borrow_date) {
        this.borrow_date = borrow_date;
    }

    // 新增：due_date（应还日期）的getter/setter方法
    public Date getDue_date() {
        return due_date;
    }

    public void setDue_date(Date due_date) {
        this.due_date = due_date;
    }

    // 新增：return_date（实际还书日期）的getter/setter方法
    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }
}
