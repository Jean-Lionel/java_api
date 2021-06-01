package com.location.Model;

public class Reservation {
	
	private int id;
	private int appartement_id;
	private int semaine_debut;
	private int nombre_semaine;
	private int semaine_fin;
	private String nom_client;
	private String telephone;
	private Appartment appartment;
	
	

	public Reservation(int appartement_id, int semaine_debut, int nombre_semaine, String nom_client, String telephone ) {
		super();
		this.appartement_id = appartement_id;
		this.semaine_debut = semaine_debut;
		this.nombre_semaine = nombre_semaine;
		this.nom_client = nom_client;
		this.telephone = telephone;
	}
	
	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAppartement_id() {
		return appartement_id;
	}

	public void setAppartement_id(int appartement_id) {
		this.appartement_id = appartement_id;
	}

	public int getSemaine_debut() {
		return semaine_debut;
	}

	public void setSemaine_debut(int semaine_debut) {
		this.semaine_debut = semaine_debut;
	}

	public int getNombre_semaine() {
		return nombre_semaine;
	}

	public void setNombre_semaine(int nombre_semaine) {
		this.nombre_semaine = nombre_semaine;
	}

	public String getNom_client() {
		return nom_client;
	}

	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Appartment getAppartment() {
		return appartment;
	}

	public void setAppartment(Appartment appartment) {
		this.appartment = appartment;
	}

	@Override
	public String toString() {
		return "Reservation [id=" + id + ", appartement_id=" + appartement_id + ", semaine_debut=" + semaine_debut
				+ ", nombre_semaine=" + nombre_semaine + ", nom_client=" + nom_client + ", telephone=" + telephone
				+ ", appartment=" + appartment + "]";
	}

	public int getSemaine_fin() {
		return semaine_fin;
	}

	public void setSemaine_fin(int semaine_fin) {
		this.semaine_fin = semaine_fin;
	}
	
	

}
