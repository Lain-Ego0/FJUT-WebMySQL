package com.demo.javabean;

// 新增：导入BigDecimal类，用于映射数据库的decimal类型价格字段
import java.math.BigDecimal;

public class Books {
    private static final long serialVersionUID = 1L;
    public static final int PAGE_SIZE=5;
    private int id;
    private String name;
    private String author;
    private String intro;
    private int amount;
    private String category;
    // 新增：对应SQL表的isbn（书号）、publisher（出版社）、price（定价）字段
    private String isbn;
    private String publisher;
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    // 新增：isbn字段的getter/setter方法，格式与原有方法保持一致
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // 新增：publisher字段的getter/setter方法
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    // 新增：price字段的getter/setter方法（类型为BigDecimal，适配数据库decimal类型）
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}