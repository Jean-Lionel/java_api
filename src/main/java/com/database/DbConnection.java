package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	private static Connection connection = null;
	
	private static void createConnection() {
		if(connection == null) {
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				String jdbcUrl = "jdbc:sqlite:C:\\Users\\Jean-Lionel\\Code\\nadine\\Location\\locationAppartement\\nadine_location";
				 connection = DriverManager.getConnection(jdbcUrl);
			} catch (SQLException e) {
				System.out.println("ERROR HERE");
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}	
		}
	}
	
	public static Connection getConnection() {
		if(connection == null)
			createConnection();
		return connection;
	}


	public static void setConnection(Connection connection) {
		DbConnection.connection = connection;
	}


	//EXECUTER UNE CHANGEMENT DANS LA BASE DE DONNES
	public static int insertIntoDb(String sql) {
		createConnection();
		try {
			Statement statement = connection.createStatement();
			return statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return 0;	
	}
	//SELECTIONNER DANS LA BASE DE DONNES
	public static ResultSet selectToDb(String sql) {
		
		try {
			Connection c = DbConnection.getConnection();
			Statement statement = c.createStatement();
			
			return statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		return null;
	}
}
