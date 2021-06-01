package com.resources;

import java.util.List;

import com.config.CorsFilter;
import com.controller.AppartementManager;
import com.location.Model.Appartment;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("appartements")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AppartementRessources extends CorsFilter{
	@GET
	public List<Appartment> getListeAppartement(){
		List<Appartment> appartements = AppartementManager.lister();
		return appartements;
	}
	@POST
	public String ajouterAppartement(Appartment appartment) {
		AppartementManager.ajouter(appartment);
		return "REUSSI";
	}
	@GET
	@Path("/{appartement_id}")
	public Appartment getAppartement(@PathParam("appartement_id") long appartement_id) {
		return AppartementManager.getAppartement(appartement_id);
	}
}
