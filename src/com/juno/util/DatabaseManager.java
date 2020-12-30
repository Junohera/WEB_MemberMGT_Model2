package com.juno.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String uid = "juno";
	private static final String pass = "juno";
	
	public static Connection getConnection() {
		Connection con = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, uid, pass);
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();}
		
		return con;
	}
	
	public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (con != null) con.close();
			if (ps != null) ps.close();
			if (rs != null) rs.close();	
		} catch (SQLException e) {e.printStackTrace();}
	}
}
