package com.ps.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.demo.dto.ReservationDTO;
import com.ps.demo.service.ReservationService;

/**
 * Reservation Controller
 * @author Neetesh.Kadam
 *
 */
@RestController
@RequestMapping(path="reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	/**
	 * View Reservation
	 * @param reservationId
	 * @return
	 */
	@GetMapping(path="{reservationId}")
	public ReservationDTO getReservation(@PathVariable long reservationId){
		return reservationService.getReservation(reservationId);
	}
	
	/**
	 * Get reservation cost
	 * @param reservationId
	 * @return
	 */
	@GetMapping(path="/{reservationId}/cost")
	public long getReservationCost(@PathVariable long reservationId){
		return reservationService.getReservationCost(reservationId);
	}
	
	/**
	 * Cancel parking slot
	 * @param reservationId
	 */
	@DeleteMapping(path="/{reservationId}")
	public void cancelReservation(@PathVariable long reservationId) {
		reservationService.cancelReservation(reservationId);
	}	
	
}
