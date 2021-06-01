package com.location.Model;

import com.controller.AppartementManager;

public class Appartment {
	private long id;
	private String name;
	private long ville_id;
	@SuppressWarnings("unused")
	private Ville ville;
	
	public Appartment(long id, String name, long ville_id) {
		super();
		this.id = id;
		this.name = name;
		this.ville_id = ville_id;
	}
	
	public Appartment(String name, long a) {
		super();
		
		this.name = name;
		this.ville_id = a;
	}
	
	@Override
	public String toString() {
		return "Appartment [id=" + id + ", name=" + name + ", ville_id=" + ville_id + "]";
	}

	public Appartment() {
		// TODO Auto-generated constructor stub
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getVille_id() {
		return ville_id;
	}
	public void setVille_id(long ville_id) {
		this.ville_id = ville_id;
	}

	public Ville getVille() {
		
		return AppartementManager.getVilleByAppartement(this.getVille_id());
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}
	

	
	

}
