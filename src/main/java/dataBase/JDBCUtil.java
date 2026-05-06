package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
	public static Connection getConnection() throws ClassNotFoundException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mywebsite";
			String username="root";
			String password="";
			
			conn= DriverManager.getConnection(url, username, password);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	public static void close(Connection conn, Statement stm, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			
			if(stm != null) {
				stm.close();
			}
			
			if(conn != null) {
				conn.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
