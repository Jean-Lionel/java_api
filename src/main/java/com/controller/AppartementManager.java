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

public class AppartementManager {
	private static  String message = "";
	public static void ajouter(Appartment appartment) {
		try {
			PreparedStatement preparedStatement = null;
			createTable();
			Connection connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO Appartements(name, ville_id) VALUES (?,?)");
			preparedStatement.setString(1, appartment.getName());
			preparedStatement.setInt(2, (int) appartment.getVille_id());
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
	
	public void supprimer(long id) {
		DbConnection.insertIntoDb("DELETE FROM Ville WHERE id = " + id);
		setMessage(message);
	}
	
	//CREATION DE LA TABLE VILLE SI LA TABLE N'EXISTE PAS
	private static void createTable() {
		String sql = "CREATE TABLE IF NOT EXISTS \"Appartements\" (\r\n"
				+ "	\"id\"	INTEGER,\r\n"
				+ "	\"name\"	INTEGER,\r\n"
				+ "	\"ville_id\"	INTEGER,\r\n"
				+ "	PRIMARY KEY(\"id\" AUTOINCREMENT)\r\n"
				+ ")";
		
		DbConnection.insertIntoDb(sql);
		
	}
	public static  List<Appartment> lister(){
		Connection connection = DbConnection.getConnection();
		
		
		Statement statement = null;
		ResultSet response = null;
		try {
			statement = connection.createStatement();
			 response = statement.executeQuery("SELECT * FROM Appartements ORDER BY id DESC");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public static String getMessage() {
		return message;
	}
	public static void setMessage(String message) {
		AppartementManager.message = message;
	}

	public static Appartment getAppartement(long appartement_id) {
		// TODO Auto-generated method stub
			ResultSet response = DbConnection.selectToDb("SELECT * FROM Appartements WHERE id="+ appartement_id);
			
			try {
				if(response!= null && response.next()) {
				 return new Appartment(response.getInt("id"),response.getString("name"), response.getInt("ville_id"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		return null;
	}
	
	//Permet de renvoyer la ville de l'appartement
	public static Ville getVilleByAppartement(long appartement_id) {
		// TODO Auto-generated method stub
			ResultSet response = DbConnection.selectToDb("SELECT * FROM Ville WHERE id="+ appartement_id);
			
			try {
				if(response!= null && response.next()) {
				 return new Ville(response.getInt("id"),response.getString("name"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		return null;
	}
	
	public static String[] listeAppartement() {
		String appartements[] = {
				"D??lice de dunes",
				"Location du dauphin",
				"R??ves sal??s",
				"Reine de la plage",
				"J???aime la plage",
				"La Calypso",
				"Changez vos",
				"habitudes",
				"Location de la falaise",
				"La falaise paradis",
				"Hotel Coconut",
				"Vacances sur les docks",
				"Le clapotis",
				"Dunes et farniente",
				"R??ves ensabl??s",
				"Escapade ",
				"familiale au soleil",
				"Une pause??? ",
				"quelque part",
				"R??ves de plages",
				"Calme sur la c??te",
				"La c??t?? magique",
				"R??ves bleus",
				"Sea",
				"wine and sund",
				"Le temps d???une ??le",
				"Juste la plage",
				"Le r??ve ??veill??",
				"Travailler ?? votre",
				"tranquillit??",
				"Retraite les pieds dans le sable",
				"Le chien sal??",
				"La plage absolue",
				"Le Paradis ultime",
				"La pointe de sel",
				"Derri??res ensabl??s",
				"Retraite les pieds dans l???eau",
				"La c??te amusante",
				"Tourn??e vers le sud",
				"Souvenirs d???antan",
				"Escapades ensoleill??es",
				"Une vie ensoleill??e",
				"Souvenirs retrouv??s",
				"Escapades marines",
				"Mer calme",
				"Hello Guadeloupe",
				"Un second souffle",
				"Jetez l???ancre",
				"Location de la c??te",
				"Poisson d???amour",
				"S??r??nit??",
				"La location de la mer",
				"La c??te vitamin??e",
				"La tranquillit?? bleue",
				"Toutes voiles dehors",
				"La mer vitamin??e",
				"Stella Maris",
				"La perle marine",
				"La plage vitamin??e",
				"Toujours ensoleill??",
				"La brise marine",
				"Souvenirs d???enfance",
				"7 palmiers",
				"La perle de l???oc??ane",
				"La mer sur vitamin??e",
				"La mer pour toujours",
				"Bleu soleil",
				"Fun and Sun",
				"Le paradis bleu",
				"Bleu intense",
				"Trop beau pour ??tre vrai",
				"Empreintes ensabl??es",
				"Bleu horizon",
				"Une exp??rience de mer",
				"Du bon et beau temps",
				"La chaleur du bleu",
				"La perle bleue",
				"Le paradis perdu",
				"Une vague de plaisir",
				"L?????le noire",
				"L?????le paradis",
				"L?????le bleue",
				"Le clapotis des flots",
				"Le bruit des vagues",
				"Bise de la plage",
				"Paradis Bikini",
				"Paradis des bulles",
				"La p??lican pourpre",
				"Bikini & Martini",
				"S??r??nit?? ?? la mer",
				"Krusty la Crabe",
				"Du sel ?? la vie",
				"Un ??t?? sans fin",
				"R??ves de mer",
				"Le paradis ?? la plage",
				"Le bungalow du sable",
				"R??ves de sables",
				"Le soupir des palmiers",
				"Le flamant rose"
		}; 
		
		return appartements;
		
	}

}
