package com.location.Model;

public class Semaine {
	private long id;
	private int numero;
	
	
	public Semaine(long id, int numero) {
		super();
		this.id = id;
		this.numero = numero;
	}

	public Semaine() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "Semaine [id=" + id + ", numero=" + numero + "]";
	}
	
}
