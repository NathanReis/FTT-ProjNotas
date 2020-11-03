package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

abstract public class ConnectionDB {
	private static Connection instance = null;
	
	public static Connection getInstance() throws SQLException, ClassNotFoundException {
		if(instance == null) {
			String urlBd = "jdbc:mysql://localhost:3306/projnotas?useTimezone=true&serverTimezone=UTC";
			String password = "nsvw@#220Z";
			String user = "es2lp3n2";
			
			Class.forName("com.mysql.jdbc.Driver");
			
			instance = DriverManager.getConnection(urlBd, user, password);
		}
		
		return instance;
	}	
	
	public static void closeInstance() throws SQLException {
		if(instance != null) {
			instance.close();
			instance = null;
		}
	}
}
