package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.database.DbConnection;
import com.location.Model.Client;

public class ClientManager {
	private static  String message = "";
	public static void ajouter(Client client) {
		try {
			PreparedStatement preparedStatement = null;
			createVilleTable();
			Connection connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO Clients(nom, prenom, email,telephone ) VALUES (?, ? , ? , ?)");
			preparedStatement.setString(1, client.getNom());
			preparedStatement.setString(2, client.getPrenom());
			preparedStatement.setString(3, client.getEmail());
			preparedStatement.setString(4, client.getTelephone());
			preparedStatement.executeUpdate();
			setMessage("REUSSI");
			
		} catch (Exception e) {
			// TODO: handle exception
			setMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void modifier(Client client) {
		try {
			PreparedStatement preparedStatement = null;
			Connection connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE Clients SET nom=? ,prenom=? , telephone=? , email =?  WHERE id=?");
			preparedStatement.setString(1, client.getNom());
			preparedStatement.setString(2, client.getPrenom());
			preparedStatement.setString(3, client.getTelephone());
			preparedStatement.setString(4, client.getEmail());
			preparedStatement.setLong(5, client.getId());
			preparedStatement.executeUpdate();
			setMessage("REUSSI");
			
		} catch (Exception e) {
			// TODO: handle exception
			setMessage(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void supprimer(long id) {
		DbConnection.insertIntoDb("DELETE FROM Ville WHERE id = " + id);
		setMessage(message);
	}
	
	//CREATION DE LA TABLE VILLE SI LA TABLE N'EXISTE PAS
	private static void createVilleTable() {
		String sql = "CREATE TABLE IF NOT EXISTS \"Clients\" (\r\n"
				+ "	\"id\"	INTEGER,\r\n"
				+ "	\"nom\"	TEXT,\r\n"
				+ "	\"prenom\"	TEXT,\r\n"
				+ "	\"email\"	TEXT,\r\n"
				+ "	\"telephone\"	TEXT,\r\n"
				+ "	PRIMARY KEY(\"id\" AUTOINCREMENT)\r\n"
				+ ");";
		
		DbConnection.insertIntoDb(sql);
		
	}
	public static  List<Client> lister(){
		Connection connection = DbConnection.getConnection();
		
		
		Statement statement = null;
		ResultSet response = null;
		try {
			statement = connection.createStatement();
			 response = statement.executeQuery("SELECT * FROM Clients ORDER BY id DESC");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Client> clients = new ArrayList<Client>();
		
		if(response != null) {
			try {
				while (response.next()) {
					Client v = new Client(response.getInt("id"), response.getString("nom"), response.getString("prenom"), response.getString("email"), response.getString("telephone"));
					clients.add(v);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return clients;
	}
	public static String getMessage() {
		return message;
	}
	public static void setMessage(String message) {
		ClientManager.message = message;
	}

	public static Client getClient(long client_id) {
		// TODO Auto-generated method stub
		ResultSet response = DbConnection.selectToDb("SELECT * FROM Clients WHERE id ="+ client_id);
		
		try {
			if(response.next()) {
				Client c= new Client(response.getInt("id"), response.getString("nom"), response.getString("prenom"), response.getString("email"), response.getString("telephone"));
				return c;
			}
			
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
