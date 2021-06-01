package com.location.test;

import com.controller.ReservationManager;
import com.location.Model.Reservation;

public class MainTest {
	public static void main(String[] args) {	
	
//		String[] liste_ville = VilleManager.getListVille();
//		
//		for (int i = 1; i < liste_ville.length; i++) {
//			VilleManager.ajouter(liste_ville[i]);
//		}

//		 String[] appartements = AppartementManager.listeAppartement();
//		 
//		 for (String appartement : appartements) {
//			 Random rand = new Random();
//			 int ville_id = (int) Math.ciel(rand.nextDouble() * 9);
//			 Appartment a = new Appartment(appartement, ville_id);
//			 AppartementManager.ajouter(a);
//			
//		}
		
// System.out.println("REUSSI");
		
//		 List<Appartment> appartements = AppartementManager.lister();
//		 
//		 for (Appartment appartment : appartements) {
//			 Random rand = new Random();
//			 int semaine_id = (int) Math.round(rand.nextDouble() * 50);
//			 
//			 JournalManager.ecrire(appartment.getId(), semaine_id);
//			
//		}
//		
		
//		 List<Appartment> a = JournalManager.getListeAppartement(12 , 5);
//		 
//		 for (Appartment appartement : a) {
//			 System.out.println(appartement);
//			
//		}
		
//		for (int i = 0; i < 40; i++) {
//			if(i % 3 == 0) {
//				System.out.println(i);
//			}
//			
//		}
		
		//Reservation res = new Reservation(12, 12, 4,  "ALORS ON DANSE", "+39 25 452 458");
	    
	    ReservationManager.anulerReservation(5);
		
		System.out.println(ReservationManager.lister());
		 
	}
}
