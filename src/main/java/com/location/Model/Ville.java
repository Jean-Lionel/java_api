package com.location.Model;

public class Ville {
	private long id;
	private String name;
	//private List<Appartment> appartments;
	
	public Ville() {
		// TODO Auto-generated constructor stub
	}
	
	public Ville(long id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Ville [id=" + id + ", name=" + name + "]";
	}

}
