package com.resources;

import java.util.List;

import com.controller.VilleManager;
import com.location.Model.Ville;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

/**
 * Root resource (exposed at "villes" path)
 */
@Path("villes")
public class VilleResource{

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	
	@GET
	@Path("/")
    @Produces(MediaType.APPLICATION_JSON)
	public Response index() {
		
		List<Ville> villes = VilleManager.lister();
	    return Response
	      .status(Status.OK)
	      .replaceAll(null)
	      .entity(villes)
	      .build();
	}
	
	@GET
	@Path("/{ville_id}")
    @Produces(MediaType.APPLICATION_JSON)
	public Response getVille(@PathParam("ville_id") long id) {
		
		Ville ville = VilleManager.getVille(id);
	    return Response
	      .status(Status.OK)
	      .replaceAll(null)
	      .entity(ville)
	      .build();
	}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String addVille(Ville ville) {
		System.out.println(ville);
		VilleManager.ajouter(ville.getName());
		return VilleManager.getMessage();
		
		//return "OK";
	}
	
	@DELETE
	@Path("/{ville_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteVille(@PathParam("ville_id") long id) {
		
		VilleManager.supprimer(id);
		return VilleManager.getMessage();
		
		//return "OK";
	}
	
	
	
}
