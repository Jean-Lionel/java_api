package com.resources;

import java.util.List;

import com.controller.ReservationManager;
import com.location.Model.Reservation;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ReservationRessource {
	
	@GET
	public List<Reservation> getReservation() {
		
		return ReservationManager.lister();
	}
	
	@POST
	public List<Reservation> addReservation(Reservation reservation) {
		
		ReservationManager.ajouter(reservation);
		
		//System.out.println(reservation);
		return ReservationManager.lister();
		
	}

}
