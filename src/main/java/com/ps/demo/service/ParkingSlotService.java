package com.ps.demo.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import com.ps.demo.dao.ParkingSlotRepository;
import com.ps.demo.dao.ReservationRepository;
import com.ps.demo.dao.VehicleOwnerRepository;
import com.ps.demo.dto.LocationSearchDTO;
import com.ps.demo.entities.ParkingSlot;
import com.ps.demo.entities.ParkingSlotLocation;
import com.ps.demo.entities.Reservation;
import com.ps.demo.mongorepo.ParkingSlotLocationRepository;

@Service
public class ParkingSlotService {
	@Autowired
	private ParkingSlotRepository parkingSlotRepository;
	
	@Autowired
	private VehicleOwnerRepository vehicleOwnerRepository;
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private ParkingSlotLocationRepository locationRepository;
	
	public List<ParkingSlot> getAllAvailalbleParkingSlots(){
		return parkingSlotRepository.getAvailableParkingSlots();
	}
	
	public List<ParkingSlot> getAllAvailalbleParkingSlots(LocationSearchDTO locationSearchDTO){
		List<ParkingSlotLocation> findByPositionNear = locationRepository.findByPositionNear(new Point(locationSearchDTO.getLongitude(), locationSearchDTO.getLatitude()), new Distance(locationSearchDTO.getRadius(), Metrics.KILOMETERS));
		List<String> uniqueLocations = findByPositionNear.stream().map(data->data.getUniqueId()).collect(Collectors.toList());
		return parkingSlotRepository.getAvailableParkingSlots(uniqueLocations);
	}
	
	public void reserveParkingSlot(long parkingSlotId, long vehicleOwnerId) {
		Reservation reservation = new Reservation();
		reservation.setFromTime(new Date());
		reservation.setParkingSlot(parkingSlotRepository.getOne(parkingSlotId));
		reservation.setVehicleOwner(vehicleOwnerRepository.getOne(vehicleOwnerId));
		
		reservationRepository.save(reservation);
	}
	
	
}
