package com.demo.dao;
import com.demo.javabean.*;
import java.sql.SQLException;

public class AdminDAO {
	DBAccess db ;
	public boolean valid(String username, String password) {
		boolean isValid = false;
		db=new DBAccess();
		if(db.createConn()) {
			try {
				String sql="select * from admins where admin=? and password=?";
				db.pre=db.getConn().prepareStatement(sql);
				db.pre.setString(1, username);
				db.pre.setString(2, password);
				db.setRs(db.pre.executeQuery());
				if(db.getRs().next()){
					isValid=true;
				}
			}catch (Exception e) {
			}finally {
				db.closeRs();
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
			try {
				String sql="select * from admins where admin=?";
				db.pre=db.getConn().prepareStatement(sql);
				db.pre.setString(1,username );
				db.setRs(db.pre.executeQuery());
				if(db.getRs().next()){
					isExist=true;
				}
			} catch (Exception e) {
			}
			finally {
				db.closeRs();
				if(db.pre!=null){
					try {
						db.pre.close();
					} catch (Exception e2) {
					}
				}
				db.closeConn();
			}
		}
		return isExist;
	}
}