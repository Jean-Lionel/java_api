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
import com.location.Model.Journal;
import com.location.Model.Semaine;
import com.location.Model.Ville;

public class JournalManager {
	
	private static  String message = "";
	
	public static void ecrire(long appartement_id , int semaine_id) {
		try {
			PreparedStatement preparedStatement = null;
			createJournalTable();
			Connection connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO journals(appartement_id, semaine_id) VALUES (?, ? )");
			preparedStatement.setLong(1, appartement_id);
			preparedStatement.setLong(2, semaine_id);
			preparedStatement.executeUpdate();
			setMessage("REUSSI");
			
		} catch (Exception e) {
			// TODO: handle exception
			setMessage(e.getMessage());
			e.printStackTrace();
		}
		
	}

	private static void createJournalTable() {
		// TODO Auto-generated method stub // VILLE DOIT ETRE UNIQUE
		String sql = "CREATE TABLE IF NOT EXISTS \"journals\" (\r\n"
				+ "	\"id\"	INTEGER,\r\n"
				+ "	\"appartement_id\"	INTEGER,\r\n"
				+ "	\"semaine_id\"	INTEGER,\r\n"
				+ "	PRIMARY KEY(\"id\" AUTOINCREMENT)\r\n"
				+ ");";
		DbConnection.insertIntoDb(sql);
		
	}
	
	public static List<Journal> lire() {
		Connection connection = DbConnection.getConnection();
		Statement statement = null;
		ResultSet response = null;
		try {
			statement = connection.createStatement();
			 response = statement.executeQuery("SELECT * FROM journals");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 List<Journal> journals = new ArrayList<Journal>();
		 
		if(response!= null) {
			try {
				while (response.next()) {
					int appartement_id = response.getInt("appartement_id");
					int semaine_id = response.getInt("semaine_id");
					Appartment a = AppartementManager.getAppartement(appartement_id);
					Semaine s = SemaineManager.getSemaine(semaine_id);
					
					journals.add(new Journal(a, s));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return journals;
	}

	public static String getMessage() {
		return message;
	}

	public static void setMessage(String message) {
		JournalManager.message = message;
	}
	
	// REPONSE DES QUESTIONS
	//la liste des villes dans lesquelles un ou plusieurs appartements sont disponibles une semaine donnée (semaine 1 à 52). 
	
	public static List<Ville> villeDisponible(int semaine_id) {
		
		//SELECTION DES APPARTEMENTS
		String sql = "SELECT * FROM Appartements WHERE id  NOT IN (SELECT appartement_id FROM journals WHERE semaine_id = "+ semaine_id+")";
		
		ResultSet response = DbConnection.selectToDb(sql);
		
		List<Appartment> appartements = new ArrayList<Appartment>();
		
		if(response != null) {
			try {
				while (response.next()) {
					Appartment a = new Appartment(response.getInt("id"),response.getString("name"), response.getInt("ville_id"));
					appartements.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		List<Ville> v = new ArrayList<Ville>();
		for (Appartment appartment : appartements) {
			Ville ville = appartment.getVille();
			 
			if(v.size()> 0) {
				boolean exist = false;
				for (Ville ville2 : v) {
					
					
					if(ville2.getId() == ville.getId()) {
						exist = true;
						break;
					}
				}
				if(!exist) {
					v.add(ville);
				}
			}else {
				v.add(ville);
			}
			
			
		}
		
		return v;
	}
	
	//- la liste des appartements disponibles dans une ville donnée et une période donnée. 
	
	//SELECT * appartement SELECT * FROM Appartements WHERE id  NOT IN (SELECT appartement_id FROM journals WHERE semaine_id = "+ semaine_id+") AND ville_id = id
	// 
	
	public static List<Appartment> getListeAppartement(int semaine_id, int ville_id) {
		
		String sql = "SELECT * FROM Appartements WHERE id  NOT IN (SELECT appartement_id FROM journals WHERE semaine_id = "+ semaine_id+") AND ville_id=" + ville_id;
		
		ResultSet response = DbConnection.selectToDb(sql);
		
		List<Appartment> appartements = new ArrayList<Appartment>();
		
		if(response != null) {
			try {
				while (response.next()) {
					Appartment a = new Appartment(response.getInt("id"),response.getString("name"), response.getInt("ville_id"));
					appartements.add(a);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return appartements;
		
	}
}
