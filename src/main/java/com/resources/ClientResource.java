package com.resources;

import java.util.List;

import com.config.CorsFilter;
import com.controller.ClientManager;
import com.location.Model.Client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource  extends CorsFilter{
	
	@GET
	public List<Client> getClientList(){
		return ClientManager.lister();
	}
	
	@POST
	public String addClient(Client c) {
		ClientManager.ajouter(c);
		return ClientManager.getMessage();
	}
	
	@GET
	@Path("/{client_id}")
	public Client getClient(@PathParam("client_id") long client_id){
		return ClientManager.getClient(client_id);
	}
	@PUT
	@Path("/{client_id}")
	public String update( @PathParam("client_id") long client_id,  Client client){
		client.setId(client_id);
		ClientManager.modifier(client);
		return ClientManager.getMessage();
	}
	

}
