package db;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DB {

	public static Statement getStatement() throws ClassNotFoundException, SQLException {		
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_restaurant", "postgres", "123");
		return connection.createStatement();
	}
	
	public static void saveUser(String user, String password) throws ClassNotFoundException, SQLException {		
		try(Statement statement = getStatement()) {
			statement.execute("insert into tb_user(name, password) values('" + user + "','" + password + "');");
		}
	}
	
	public static int getIdUser(String user, String password) throws ClassNotFoundException, SQLException {
		try(Statement statement = getStatement()) {			
			ResultSet rs = statement.executeQuery("select id from tb_user where name='" + user + "' and password='" + password + "'");
			while(rs.next()) {
				return rs.getInt("id");
			}
			return -1;
		}
	}
	
	public static void insertSQL(String sql) throws ClassNotFoundException, SQLException {
		try(Statement s = getStatement()) {
			s.execute(sql);
		}
	}
	
	public static List<String> getListStrings(String sql) throws ClassNotFoundException, SQLException {
		List<String> l = new LinkedList<>();
		try(Statement s = getStatement()) {
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				l.add(rs.getString("name"));
			}
		}
		return l;
	}
	
	public static Map<Integer, String> getIdName(String sql) throws ClassNotFoundException, SQLException {
		Map<Integer, String> m = new HashMap<>();
		try(Statement s = getStatement()) {
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				m.put(rs.getInt("id"), rs.getString("name"));
			}
		}
		return m;
	}
	
	public static int getId(String sql) throws ClassNotFoundException, SQLException {
		int id = -1;
		try(Statement s = getStatement()) {
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) {
				return rs.getInt("id");
			}
		}
		return id;
	}
	
	public static int getCount(String sql) throws ClassNotFoundException, SQLException {
		int count = 0;
		try(Statement s = getStatement()) {
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()) count++;
		}
		return count;
	}
}
