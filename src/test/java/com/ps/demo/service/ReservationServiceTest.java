package com.ps.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ps.demo.dao.ReservationRepository;
import com.ps.demo.dto.ReservationDTO;
import com.ps.demo.entities.ParkingSlot;
import com.ps.demo.entities.Reservation;
import com.ps.demo.entities.VehicleOwner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {

	@Mock
	private ReservationRepository reservationRepository;
	
	@InjectMocks
	private ReservationService reservationService;	
	
	/**
	 * Test for {@link ReservationService#getReservation(long)}
	 */
	@Test
	public void testGetReservation() {
		Reservation reservation = new Reservation();
		reservation.setId(1L);
		ParkingSlot parkingSlot = new ParkingSlot();
		parkingSlot.setId(1L);
		VehicleOwner vehicleOwner = new VehicleOwner();
		vehicleOwner.setId(1L);
		reservation.setParkingSlot(parkingSlot);
		reservation.setVehicleOwner(vehicleOwner);
		Long reservationId = 1L;
		when(reservationRepository.getOne(reservationId)).thenReturn(reservation);
		ReservationDTO reservationDTO = reservationService.getReservation(reservationId);
		assertNotNull(reservationDTO);
		assertEquals(1L, reservationDTO.getId());
		assertEquals(1L, reservationDTO.getParkingSlotId());
		assertEquals(1L, reservationDTO.getVehicleOwnerId());
	}
	
	/**
	 * Test for {@link ReservationService#getReservationCost(long)}
	 */
	@Test
	public void testGetReservationCost() {
		Reservation reservation = new Reservation();
		reservation.setId(1L);
		reservation.setFromTime(new Date());
		reservation.setToTime(new Date());
		ParkingSlot parkingSlot = new ParkingSlot();
		parkingSlot.setId(1L);
		parkingSlot.setCostPerHour(10);
		VehicleOwner vehicleOwner = new VehicleOwner();
		vehicleOwner.setId(1L);
		reservation.setParkingSlot(parkingSlot);
		reservation.setVehicleOwner(vehicleOwner);
		Long reservationId = 1L;
		when(reservationRepository.getOne(reservationId)).thenReturn(reservation);
		long cost = reservationService.getReservationCost(reservationId);
		assertEquals(0L, cost);
		
		reservation.setToTime(null);
		when(reservationRepository.getOne(reservationId)).thenReturn(reservation);
		cost = reservationService.getReservationCost(reservationId);
		assertEquals(0L, cost);
		
		DateTime dtFrom = new DateTime().withHourOfDay(5);
		DateTime dtTo = new DateTime().withHourOfDay(7);
		reservation.setFromTime(dtFrom.toDate());
		reservation.setToTime(dtTo.toDate());
		when(reservationRepository.getOne(reservationId)).thenReturn(reservation);
		cost = reservationService.getReservationCost(reservationId);
		assertEquals(20L, cost);
	}
	
	/**
	 * Test for {@link ReservationService#cancelReservation(long)}
	 */
	@Test
	public void testCancelReservation() {
		Reservation reservation = new Reservation();
		reservation.setId(1L);
		ParkingSlot parkingSlot = new ParkingSlot();
		parkingSlot.setId(1L);
		VehicleOwner vehicleOwner = new VehicleOwner();
		vehicleOwner.setId(1L);
		reservation.setParkingSlot(parkingSlot);
		reservation.setVehicleOwner(vehicleOwner);
		Long reservationId = 1L;
		when(reservationRepository.getOne(reservationId)).thenReturn(reservation);
		reservationService.cancelReservation(reservationId);
	}
	
}
