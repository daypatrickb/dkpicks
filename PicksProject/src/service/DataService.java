package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import model.Game;

public class DataService {


	
	
	
	public static Game getGameByID(long id)
	{
		
	
		
		return null;
	}
	
	
	
	// don't use this unless necessary, prefer HQL and named queries
	private static class ConnectionManager 
	{
		
		public static Connection getConnection() throws Exception
		{
			Connection conn = null;
			String url = "jdbc:mysql://localhost/";
			String dbName = "picks";
			String driver = "com.mysql.jdbc.Driver";
			String userName = "picks";
			String password = "picks";
			try {
			  Class.forName(driver).newInstance();
			  conn = DriverManager.getConnection(url+dbName,userName,password);
			  System.out.println("Connected to the database");
		
			} catch (Exception e) {
			    System.out.println("NO CONNECTION =(");
			}
			return conn;
		}

	}
	
	
}

