package com.resources;

import java.util.List;

import com.controller.JournalManager;
import com.location.Model.Appartment;
import com.location.Model.Journal;
import com.location.Model.Ville;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("journals")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JournalResource {
	
	@GET
	public List<Journal> getJournal(){
		return JournalManager.lire();
	}
	
	//la liste des appartements disponibles dans une ville donnée et une période donnée. 
	
	@GET
	@Path("{semaine_id}/{ville_id}")
	public List<Appartment> appartement_disponible(@PathParam("semaine_id") int semaine_id, @PathParam("ville_id") int ville_id) {
		
		//System.out.println("METHO CALLED SEMAINE : " +semaine_id + " VILLE " +ville_id );
		
		List<Appartment> a = JournalManager.getListeAppartement(semaine_id , ville_id);
		//System.out.println(a);
		
		return a;
	}
	//La liste des villes dans laquel les appartements sont disponible 
	@GET
	@Path("{semaine_id}")
	public List<Ville> getvilleDisponibleBySemaine(@PathParam("semaine_id") int semaine_id){
		return JournalManager.villeDisponible(semaine_id);
	
	}
	
	//LISTE DES RESERVATION 
	
}
