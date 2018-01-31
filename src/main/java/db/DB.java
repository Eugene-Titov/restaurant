package db;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

	public static Connection getConnection() throws ClassNotFoundException, SQLException {		
		return DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_restaurant", "postgres", "123");
	}
	
	public static void saveUser(String user, String password) throws ClassNotFoundException, SQLException {		
		try(Connection connection = getConnection(); 
			Statement statement = connection.createStatement()) {
			statement.execute("insert into tb_user(name, password) values('" + user + "','" + password + "');");
		}
	}
	
	
	
}
