package com.ps.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.demo.dto.LocationSearchDTO;
import com.ps.demo.entities.ParkingSlot;
import com.ps.demo.service.ParkingSlotService;

/**
 * Parking slot controller
 * @author Neetesh.Kadam
 *
 */
@RestController
@RequestMapping(path="parkingSlots")
public class ParkingSlotController {
	@Autowired
	private ParkingSlotService parkingSlotService;
	
	/**
	 * See available parking slot on map
	 * @return list of @ParkingSlot
	 */
	@GetMapping(path="available")
	public List<ParkingSlot> getAllAvailalbleParkingSlots(){
		return parkingSlotService.getAllAvailalbleParkingSlots();
	}

	/**
	 * Search available parking slot based on longitude, latitude and for given radius
	 * @param locationSearchDTO
	 * @return list of @ParkingSlot
	 */
	@PostMapping(path="searchAvailable")
	public List<ParkingSlot> searchParkingSlots(@RequestBody LocationSearchDTO locationSearchDTO){
		return parkingSlotService.getAllAvailalbleParkingSlots(locationSearchDTO);
	}
	
	/**
	 * Reserve parking slot
	 * @param parkingSlotId
	 * @param vehicleOwnerId
	 */
	@PostMapping(path="{parkingSlotId}/reservations/vehicleOwner/{vehicleOwnerId}")
	public void reserveParkingSlot(@PathVariable long parkingSlotId, @PathVariable long vehicleOwnerId) {
		parkingSlotService.reserveParkingSlot(parkingSlotId, vehicleOwnerId);
	}
}
