package com.ps.demo.service;

import java.util.Date;

import org.joda.time.Interval;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.demo.dao.ReservationRepository;
import com.ps.demo.dto.ReservationDTO;
import com.ps.demo.entities.Reservation;

@Service
public class ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	/**
	 * get reservation
	 * @param reservationId
	 * @return
	 */
	public ReservationDTO getReservation(long reservationId) {
		Reservation reservation = reservationRepository.getOne(reservationId);
		
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setId(reservation.getId());
		reservationDTO.setFromTime(reservation.getFromTime());
		reservationDTO.setToTime(reservation.getToTime());
		reservationDTO.setParkingSlotId(reservation.getParkingSlot().getId());
		reservationDTO.setVehicleOwnerId(reservation.getVehicleOwner().getId());
		return reservationDTO;
	}
	
	/**
	 * Get cost of reservation
	 * @param reservationId
	 * @return
	 */
	public long getReservationCost(long reservationId) {
		Reservation reservation = reservationRepository.getOne(reservationId);
		long costPerHour = reservation.getParkingSlot().getCostPerHour();
		if(reservation.getToTime()==null) {
			reservation.setToTime(new Date());
		}
		Period period = new Interval(reservation.getFromTime().getTime(), reservation.getToTime().getTime()).toPeriod();
		return costPerHour*period.getHours();
	}
	
	/**
	 * Cancel reservation
	 * @param reservationId
	 */
	public void cancelReservation(long reservationId) {
		Reservation reservation = reservationRepository.getOne(reservationId);
		reservation.setToTime(new Date());
		
		reservationRepository.save(reservation);
	}

}
