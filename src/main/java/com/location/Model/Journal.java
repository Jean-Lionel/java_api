package com.location.Model;

public class Journal {
	private Appartment appartment;
	private Semaine semaine;
	
	public Journal(Appartment appartment, Semaine semaine) {
		super();
		this.appartment = appartment;
		this.semaine = semaine;
	}

	public Appartment getAppartment() {
		return appartment;
	}

	public void setAppartment(Appartment appartment) {
		this.appartment = appartment;
	}

	public Semaine getSemaine() {
		return semaine;
	}

	public void setSemaine(Semaine semaine) {
		this.semaine = semaine;
	}

	@Override
	public String toString() {
		return "Journal [appartment=" + appartment + ", semaine=" + semaine + "]";
	}
	
	
	
	

}
