package com.android.utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * get connection with database.
 * @author santiago
 *
 */
public class DBFactory {
	//default settings
	private static String url = "jdbc:mysql://localhost:3306/user";
	private static String user = "root";
	private static String password = "santi";
	
	private static Connection conn = null;
	
	public static Connection getConnection(){
		if(conn == null) return conn = getConnection(url, user, password);
		else return conn;
	}
	
	
	public static Connection getConnection(String url, String user, String password){
		Connection conn = null;
		try{
			//load db driver.
			Class.forName("com.mysql.jdbc.Driver");
			//get connection.
			conn = (Connection) DriverManager.getConnection(url, user, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConnection(){
		try {
			if(conn != null){
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
