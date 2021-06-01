package com.resources;

import java.util.List;

import com.config.CorsFilter;
import com.controller.SemaineManager;
import com.location.Model.Semaine;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("semaines")
public class SemaineResource extends CorsFilter{
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Semaine> getSemaineList() {
		return SemaineManager.lister();
	}
	
	@GET
	@Path("/{semaineId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Semaine getSemaine(@PathParam("semaineId") String semaineId) {
		Semaine s= SemaineManager.getSemaine(Integer.parseInt(semaineId));
		
		return s;
	}
	
	@PUT
	@Path("/{semaineId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(@PathParam("semaineId") long semaineId, Semaine semaine) {
		semaine.setId(semaineId);
		return SemaineManager.getMessage();
	}
	
	
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Semaine> addSemaine() {
		for (int i = 1; i < 51; i++) {
			//SemaineManager.ajouter(i);
		}
		return SemaineManager.lister();
	}
	
	@DELETE
	@Path("/{semaineId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int deleteSemaines(@PathParam("semaineId")  long semaineId) {
		return SemaineManager.deleteSemaine(semaineId);
	}
	

}
