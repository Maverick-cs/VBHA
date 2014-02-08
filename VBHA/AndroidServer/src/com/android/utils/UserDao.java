package com.android.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

public class UserDao {
	private static Connection conn;
	static{
		conn = DBFactory.getConnection();
	}
	
	/**
	 * User login.
	 * 
	 * @param name
	 * @param pwd
	 * @return whether success
	 */
	public boolean login(String name, String pwd){
		boolean flag = false;
		String sql = "select * from users where name=? and pwd=?";
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()){
				cnt ++;
			}
			flag = cnt > 0 ? true : false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * register User
	 * @param user
	 * @return whether success
	 */
	public boolean register(User user){
		boolean flag = false;
		if(isUserExist(user.name)){
			System.out.println("name has existed, please user another name");
			return flag;
		}
		String sql = "insert into users(name, pwd, age, email) values(?,?,?,?)";
		try{
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, user.name);
			pstmt.setString(2, user.pwd);
			pstmt.setInt(3, user.age);
			pstmt.setString(4, user.email);
			
			int cnt = pstmt.executeUpdate();
			if(cnt > 0) flag = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * check whether specified name user existed.
	 * @param name
	 * @return
	 */
	public boolean isUserExist(String name){
		boolean flag = false;
		String sql = "select * from users where name=?";
		try {
			PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, name);
			
			ResultSet rs = pstmt.executeQuery();
			int cnt = 0;
			while(rs.next()){
				cnt ++;
			}
			flag = cnt > 0 ? true : false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;		
	}
	
	public static void main(String[] args){
		UserDao dao = new UserDao();
		boolean flag = dao.register(new User("admin", "admin", 20, "abc@gmail.com"));
		System.out.println("register: " + flag);
		System.out.println(dao.login("admin", "admin"));
	}

}
