package com.demo.dao;
import com.demo.javabean.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.math.BigDecimal; // 新增：导入BigDecimal，适配price字段类型

public class BookDAO  {
    DBAccess db;
    private Books book;

    public ArrayList<Books> getAllBooks() throws Exception{
        db=new DBAccess();
        ArrayList<Books> books = new ArrayList<Books>();
        if(db.createConn()){
            String sql= "SELECT * FROM books";
            db.query(sql);
            while(db.getRs().next()){
                book = this.assemble(db.getRs()); // 依赖assemble方法，已补充新增字段映射
                books.add(book);
            }
        }
        db.closeRs();
        db.closeStm();
        db.closeConn();
        return books;
    }

    public ArrayList<Books> getBooksByCategory(String category) throws Exception{    //在这里进行分页判断
        ArrayList<Books> books = new ArrayList<Books>();
        DBAccess db=new DBAccess();
        java.sql.PreparedStatement pre = null;
        if(db.createConn()){
            String sql = "select * from books where category = ? ";
            pre=db.getConn().prepareStatement(sql);
            pre.setString(1, category);
            db.setRs(pre.executeQuery());
            while(db.getRs().next()){
                book = this.assemble(db.getRs()); // 依赖assemble方法，已补充新增字段映射
                books.add(book);
            }
        }
        db.closeRs();
        db.closeStm();
        pre.close();
        db.closeConn();
        int a=books.size();
        System.out.println(a);
        return books;
    }

    // 修改：新增isbn、publisher、price参数，同步更新插入SQL
    public void addBook(String name,String author,String intro,String amount,String category,
                        String isbn, String publisher, String price) throws Exception{ // 新增三个参数
        DBAccess db = new DBAccess();
        if(db.createConn()){
            // 补充isbn、publisher、price字段到插入SQL中
            String sql = "insert into books(name,author,intro,amount,category,isbn,publisher,price) "
                    + "values('"+name+"','"+author+"','"+intro+"','"+amount+"','"+category+"','"+isbn+"','"+publisher+"','"+price+"')";
            db.update(sql);
            db.closeStm();
            db.closeConn();
        }
    }

    // 保留原有addBook方法（兼容旧代码调用，若不需要可删除，此处保留兼容性）
    public void addBook(String name,String author,String intro,String amount,String category) throws Exception{
        // 调用新增参数的addBook方法，默认给新增字段传空值
        this.addBook(name, author, intro, amount, category, "", "", "0.00");
    }

    public boolean delbook(String name)throws Exception{     //删除书籍
        DBAccess db = new DBAccess();
        boolean flag=false;
        if(db.createConn()){
            String sql="delete from books where name = ?";
            db.pre=db.getConn().prepareStatement(sql);
            db.pre.setString(1, name);
            if(db.pre.executeUpdate()>0)flag=true;
        }
        db.closeRs();
        db.closeStm();
        db.pre.close();
        db.closeConn();
        return flag;
    }

    public boolean isExist(String name) {
        boolean isExist = false;
        DBAccess db = new DBAccess();
        if(db.createConn()) {
            String sql = "select * from books where name='"+name+"'";
            db.query(sql);
            if(db.next()) {
                isExist = true;
            }
            db.closeRs();
            db.closeStm();
            db.closeConn();
        }
        return isExist;
    }

    public boolean updateBook(Books book) throws Exception{  //借还更新主要书籍数量
        boolean flag = false;
        DBAccess db=new DBAccess();
        String sql = "UPDATE books SET amount=? WHERE id=?";
        java.sql.PreparedStatement pre=null;
        if(db.createConn()){
            pre=db.getConn().prepareStatement(sql);
            pre.setInt(1,book.getAmount());
            pre.setInt(2,book.getId());
            if(pre.executeUpdate() > 0) flag = true;
        }
        db.closeRs();
        db.closeStm();
        pre.close();
        db.closeConn();
        return flag;
    }

    // 修改：补充isbn、publisher、price字段的更新
    public boolean updateBook2(Books book) throws Exception{  //修改更新图书信息
        boolean flag = false;
        DBAccess db=new DBAccess();
        if(db.createConn()){
            // 新增isbn、publisher、price字段到更新SQL中
            String sql="UPDATE books SET name=?,author=?,isbn=?,publisher=?,price=?,amount=?,category=? where id=?";
            db.pre=db.getConn().prepareStatement(sql);
            db.pre.setString(1, book.getName());
            db.pre.setString(2, book.getAuthor());
            db.pre.setString(3, book.getIsbn()); // 新增：设置isbn
            db.pre.setString(4, book.getPublisher()); // 新增：设置publisher
            db.pre.setBigDecimal(5, book.getPrice()); // 新增：设置price（BigDecimal类型）
            db.pre.setInt(6, book.getAmount());
            db.pre.setString(7, book.getCategory());
            db.pre.setInt(8, book.getId());
            if(db.pre.executeUpdate() > 0) flag = true;
        }
        db.closeRs();
        db.closeStm();
        db.pre.close();
        db.closeConn();
        return flag;
    }

    public Books getBookById(int id) throws Exception{
        DBAccess db=new DBAccess();
        String sql = "SELECT * FROM books WHERE id = ?";
        java.sql.PreparedStatement pre=null;
        if(db.createConn()){
            pre=db.getConn().prepareStatement(sql);
            pre.setInt(1,id);
            db.setRs(pre.executeQuery());
            if (db.getRs().next()) book = this.assemble(db.getRs()); // 依赖assemble方法，已补充新增字段映射
        }
        db.closeRs();
        db.closeStm();
        pre.close();
        db.closeConn();
        return book;
    }

    // 核心修改：补充isbn、publisher、price字段的映射
    public Books assemble(ResultSet rs) throws Exception{
        book = new Books();
        book.setId(rs.getInt("id"));
        book.setName(rs.getString("name"));
        book.setAuthor(rs.getString("author"));
        book.setIntro(rs.getString("intro"));
        book.setAmount(rs.getInt("amount"));
        book.setCategory(rs.getString("category"));
        // 新增：映射三个新增字段
        book.setIsbn(rs.getString("isbn"));
        book.setPublisher(rs.getString("publisher"));
        book.setPrice(rs.getBigDecimal("price"));
        return book;
    }

    public ArrayList<Books> findAll(Integer page, String name){   //分页查询信息
        DBAccess  db=new DBAccess();
        ArrayList<Books> list=new ArrayList<Books>();
        try {
            if(db.createConn()){
                StringBuilder sql = new StringBuilder("select * from books where 1=1");
                if (name != null && !name.isEmpty()) {
                    sql.append(" and name like ?");
                }
                sql.append(" limit ?,?");

                db.pre=db.getConn().prepareStatement(sql.toString());
                int index = 1;
                if (name != null && !name.isEmpty()) {
                    db.pre.setString(index++, "%" + name + "%");
                }
                db.pre.setInt(index++, (page-1)*Students.PAGE_SIZE);
                db.pre.setInt(index, Students.PAGE_SIZE);

                db.setRs(db.pre.executeQuery());
                while(db.getRs().next()){
                    Books book=new Books();
                    book.setId(db.getRs().getInt("id"));
                    book.setName(db.getRs().getString("name"));
                    book.setAuthor(db.getRs().getString("author"));
                    book.setIntro(db.getRs().getString("intro"));
                    book.setCategory(db.getRs().getString("category"));
                    book.setAmount(db.getRs().getInt("amount"));
                    // 新增：补充三个字段的赋值
                    book.setIsbn(db.getRs().getString("isbn"));
                    book.setPublisher(db.getRs().getString("publisher"));
                    book.setPrice(db.getRs().getBigDecimal("price"));
                    list.add(book);
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

    public ArrayList<Books> getBooksByCategory(String category,Integer page) throws Exception{       //在这里进行分页判断(学生端)
        ArrayList<Books> books = new ArrayList<Books>();
        DBAccess db=new DBAccess();
        java.sql.PreparedStatement pre = null;
        if(db.createConn()){
            String sql = "select * from books where category = ? limit ?,?";
            pre=db.getConn().prepareStatement(sql);
            pre.setString(1, category);
            pre.setInt(2, (page-1)*Students.PAGE_SIZE);
            pre.setInt(3, Students.PAGE_SIZE);
            db.setRs(pre.executeQuery());
            while(db.getRs().next()){
                book = this.assemble(db.getRs()); // 依赖assemble方法，已补充新增字段映射
                books.add(book);
            }
        }
        db.closeRs();
        db.closeStm();
        pre.close();
        db.closeConn();
        int a=books.size();
        System.out.println(a);
        return books;
    }

    public int countPage(String singal){    //查询记录总数
        DBAccess  db=new DBAccess();
        int count=0;
        try {
            if(db.createConn()){
                String sql;
                if(!"1".equals(singal)){
                    sql="select count(*) from books where category=?";
                    db.pre=db.getConn().prepareStatement(sql);
                    db.pre.setString(1, singal);
                    db.setRs(db.pre.executeQuery());
                }
                else {
                    sql="select count(*) from books";
                    db.pre=db.getConn().prepareStatement(sql);
                    db.setRs(db.pre.executeQuery());
                }
                if(db.getRs().next()){
                    count=db.getRs().getInt(1);
                }
            }
        } catch( SQLException e) {
            e.printStackTrace();
        }
        finally {
            db.closeRs();
            try {
                if(db.pre!=null){
                    db.pre.close();
                }
            } catch (Exception e2) {
            }
            db.closeConn();
        }
        return count;
    }

    // 修复核心：补全addBook(Books book)的实现逻辑（原空方法）
    public void addBook(Books book) throws Exception {
        DBAccess db = new DBAccess();
        if(db.createConn()){
            // 使用预编译SQL避免SQL注入，拼接所有字段
            String sql = "insert into books(name,author,intro,amount,category,isbn,publisher,price) "
                    + "values(?,?,?,?,?,?,?,?)";
            java.sql.PreparedStatement pre = db.getConn().prepareStatement(sql);
            pre.setString(1, book.getName());
            pre.setString(2, book.getAuthor());
            pre.setString(3, book.getIntro());
            pre.setInt(4, book.getAmount());
            pre.setString(5, book.getCategory());
            pre.setString(6, book.getIsbn());
            pre.setString(7, book.getPublisher());
            pre.setBigDecimal(8, book.getPrice());
            // 执行插入操作
            pre.executeUpdate();

            // 关闭资源
            pre.close();
            db.closeStm();
            db.closeConn();
        }
    }
}