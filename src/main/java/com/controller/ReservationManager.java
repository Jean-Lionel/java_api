package com.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.database.DbConnection;
import com.location.Model.Reservation;

public class ReservationManager {
	
	private static  String message = "";
	public static void ajouter(Reservation reservation) {
		
		//System.out.println(reservation.getAppartement_id());
		try {
			PreparedStatement preparedStatement = null;
			createTable();
			Connection connection = DbConnection.getConnection();
			preparedStatement = connection.prepareStatement("INSERT INTO reservations"
					+ "(appartement_id,semaine_debut,nombre_semaine,semaine_fin,nom_client,telephone) VALUES "
					+ "(?, ? , ? , ? , ? , ?)");
			
			preparedStatement.setInt(1, reservation.getAppartement_id());
			preparedStatement.setInt(2, reservation.getSemaine_debut());
			preparedStatement.setInt(3, reservation.getNombre_semaine());
			preparedStatement.setInt(4, (reservation.getNombre_semaine() + reservation.getSemaine_debut()));
			preparedStatement.setString(5, reservation.getNom_client());
			preparedStatement.setString(6, reservation.getTelephone());
			preparedStatement.executeUpdate();
			setMessage("REUSSI");
			
		} catch (Exception e) {
			// TODO: handle exception
			setMessage(e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	public static  List<Reservation> lister(){
		Connection connection = DbConnection.getConnection();
		
		Statement statement = null;
		ResultSet response = null;
		try {
			statement = connection.createStatement();
			 response = statement.executeQuery("SELECT * FROM reservations");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Reservation> reservations = new ArrayList<Reservation>();
		
		if(response != null) {
			try {
				while (response.next()) {
					Reservation r = new Reservation();
					
					r.setId(response.getInt("id"));
					r.setAppartement_id(response.getInt("appartement_id"));
					r.setSemaine_debut(response.getInt("semaine_debut"));
					r.setNombre_semaine(response.getInt("nombre_semaine"));
					r.setSemaine_fin(response.getInt("semaine_fin"));
					r.setNom_client(response.getString("nom_client"));
					r.setTelephone(response.getString("telephone"));
					
					reservations.add(r);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				setMessage(e.getMessage());
			}
			
		}
		
		return reservations;
	}
	
	
	//CREATION DE LA TABLE RESERVATION SI LA TABLE N'EXISTE PAS
	private static void createTable() {
		System.out.println("METHOD CALLED");
		
		String sql = "CREATE TABLE IF NOT EXISTS \"reservations\" (\r\n"
				+ "	\"id\"	INTEGER,\r\n"
				+ "	\"appartement_id\"	INTEGER NOT NULL,\r\n"
				+ "	\"semaine_debut\"	INTEGER NOT NULL,\r\n"
				+ "	\"nombre_semaine\"	INTEGER NOT NULL,\r\n"
				+ "	\"semaine_fin\"	INTEGER NOT NULL,\r\n"
				+ "	\"nom_client\"	TEXT,\r\n"
				+ "	\"telephone\"	TEXT,\r\n"
				+ "	PRIMARY KEY(\"id\" AUTOINCREMENT)\r\n"
				+ ")";
		
		DbConnection.insertIntoDb(sql);
		
	}
	
	
	public static int anulerReservation(int id) {
		
		String sql = "DELETE FROM reservations WHERE id="+id;
		return DbConnection.insertIntoDb(sql);
	}


	public static String getMessage() {
		return message;
	}


	public static void setMessage(String message) {
		ReservationManager.message = message;
	}
	

}
