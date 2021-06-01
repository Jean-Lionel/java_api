package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.database.DbConnection;
import com.location.Model.Appartment;
import com.location.Model.Ville;

public class VilleManager {
	private static  String message = "";
	public static void ajouter(String villeName) {
		try {
			PreparedStatement preparedStatement = null;
			createVilleTable();
			Connection connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO Ville(name) VALUES (?)");
			preparedStatement.setString(1, villeName);
			preparedStatement.executeUpdate();
			setMessage("REUSSI");
			
		} catch (Exception e) {
			// TODO: handle exception
			setMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void modifier(Ville ville) {
		try {
			PreparedStatement preparedStatement = null;
			createVilleTable();
			Connection connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE Ville SET name=? WHERE id=?");
			preparedStatement.setString(1, ville.getName());
			preparedStatement.setLong(1, ville.getId());
			preparedStatement.executeUpdate();
			setMessage("REUSSI");
			
		} catch (Exception e) {
			// TODO: handle exception
			setMessage(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public static void supprimer(long id) {
		DbConnection.insertIntoDb("DELETE FROM Ville WHERE id = " + id);
		setMessage(message);
	}
	public static Ville getVille(long id) {
		ResultSet response = DbConnection.selectToDb("SELECT * FROM Ville WHERE id = " + id);
		try {
			if(response != null && response.next()) {
				return new Ville(response.getLong("id"), response.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<Appartment> getAppartement(int ville_id) {
		ResultSet response = DbConnection.selectToDb("SELECT * FROM Appartements WHERE ville_id = " + ville_id);
		List<Appartment> appartments = new ArrayList<Appartment>();
		if(response != null) {
			try {
				while (response.next()) {
					Appartment a = new Appartment(response.getInt("id"),response.getString("name"), response.getInt("ville_id"));
					appartments.add(a);	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return appartments;
	}
	
	//CREATION DE LA TABLE VILLE SI LA TABLE N'EXISTE PAS
	private static void createVilleTable() {
		String sql = "CREATE TABLE IF NOT EXISTS \"Ville\" (\r\n"
				+ "	\"id\"	INTEGER,\r\n"
				+ "	\"name\"	TEXT,\r\n"
				+ "	PRIMARY KEY(\"id\" AUTOINCREMENT)\r\n"
				+ ");";
		
		DbConnection.insertIntoDb(sql);
		
	}
	public static  List<Ville> lister(){
		Connection connection = DbConnection.getConnection();
		
		Statement statement = null;
		ResultSet response = null;
		try {
			statement = connection.createStatement();
			 response = statement.executeQuery("SELECT * FROM Ville");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Ville> villes = new ArrayList<Ville>();
		
		if(response != null) {
			try {
				while (response.next()) {
					Ville v = new Ville(response.getLong("id"),response.getString("name"));
					villes.add(v);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				setMessage(e.getMessage());
			}
			
		}
		
		return villes;
	}
	public static String getMessage() {
		return message;
	}
	public static void setMessage(String message) {
		VilleManager.message = message;
	}
	
	public static String[] getListVille() {
		
		String villes[] = {
				"Paris",
				"Marseille",
				"Lyon",
				"Toulouse",
				"Nice",
				"Nantes",
				"Montpellier",
				"Strasbourg",
				"Bordeaux",
				"Lille",
				"Rennes",
				"Reims",
				"Toulon",
				"Saint-Étienne",
				"Le Havre",
				"Grenoble",
				"Dijon",
				"Angers",
				"Villeurbanne",
				"Saint-Denis",
				"Nîmes",
				"Clermont-Ferrand",
				"Le Mans",
				"Aix-en-Provence",
				"Brest",
				"Tours",
				"Amiens",
				"Limoges",
				"Annecy",
				"Boulogne-Billancourt",
				"Perpignan",
				"Besançon",
				"Metz",
				"Orléans",
				"Saint-Denis",
				"Rouen",
				"Argenteuil",
				"Montreuil",
				"Mulhouse",
				"Caen",
				"Nancy",
				"Saint-Paul",
				"Roubaix",
				"Tourcoing",
				"Nanterre",
				"Vitry-sur-Seine",
				"Créteil",
				"Avignon",
				"Poitiers",
				"Aubervilliers"};
		
		return villes;
	}
}
