package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.database.DbConnection;
import com.location.Model.Semaine;

public class SemaineManager {
	
	private static  String message = "";
	public static void ajouter(int numero) {
		try {
			PreparedStatement preparedStatement = null;
			createTable();
			Connection connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO Semaines(numero) VALUES (?)");
			preparedStatement.setLong(1, numero);
			preparedStatement.executeUpdate();
			setMessage("REUSSI");
			
		} catch (Exception e) {
			// TODO: handle exception
			setMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public static Semaine getSemaine(int id) {
		ResultSet response = DbConnection.selectToDb("SELECT * FROM Semaines WHERE id="+id);
		Semaine semaine = null;
		try {
			if (response != null && response.next()) {
				try {
					semaine = new Semaine();
					semaine.setId(response.getLong("id"));
					semaine.setNumero(response.getInt("numero"));
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					setMessage(e.getMessage());
					
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return semaine;
		
	}
	
	//CREATION DE LA TABLE VILLE SI LA TABLE N'EXISTE PAS
		private static void createTable() {
			String sql = "CREATE TABLE IF NOT EXISTS \"Semaines\" (\r\n"
					+ "	\"id\"	INTEGER,\r\n"
					+ "	\"numero\"	INTEGER,\r\n"
					+ "	PRIMARY KEY(\"id\" AUTOINCREMENT)\r\n"
					+ ")";
			
			DbConnection.insertIntoDb(sql);
			
		}
		public static int deleteSemaine(long id) {
			return DbConnection.insertIntoDb("DELETE FROM Semaines WHERE id="+id);
			
		}
		
		public static  List<Semaine> lister(){
			Connection connection = DbConnection.getConnection();
			Statement statement = null;
			ResultSet response = null;
			try {
				statement = connection.createStatement();
				 response = statement.executeQuery("SELECT * FROM Semaines  LIMIT 50");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<Semaine> villes = new ArrayList<Semaine>();
			
			if(response != null) {
				try {
					while (response.next()) {
						Semaine v = new Semaine(response.getLong("id"),response.getInt("numero"));
						villes.add(v);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			return villes;
		}
		
		public static String getMessage() {
			return message;
		}

		public static void setMessage(String message) {
			SemaineManager.message = message;
		}


		public static Semaine updateSemaine(Semaine semaine) {
			// TODO Auto-generated method stub
			
			String sql = "UPDATE Semaines SET numero = "+ semaine.getNumero()
							+" WHERE id= " + semaine.getId() ;
			
			DbConnection.insertIntoDb(sql);
			
			return semaine;
		}
		
		public static int[] getSemaineList() {
			int semaines[] = {};
			
			for (int i = 1; i <= 50; i++) {
				semaines[i] = i;
			}
			
			return semaines;
			
		}
	

}
