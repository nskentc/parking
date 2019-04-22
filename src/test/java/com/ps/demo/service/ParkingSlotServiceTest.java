package com.ps.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.test.context.junit4.SpringRunner;

import com.ps.demo.dao.ParkingSlotRepository;
import com.ps.demo.dao.ReservationRepository;
import com.ps.demo.dao.VehicleOwnerRepository;
import com.ps.demo.dto.LocationSearchDTO;
import com.ps.demo.entities.ParkingSlot;
import com.ps.demo.entities.ParkingSlotLocation;
import com.ps.demo.entities.Reservation;
import com.ps.demo.mongorepo.ParkingSlotLocationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingSlotServiceTest {

	@Mock
	private ParkingSlotRepository parkingSlotRepository;

	@Mock
	private VehicleOwnerRepository vehicleOwnerRepository;

	@Mock
	private ReservationRepository reservationRepository;
	
	@Mock
	private ParkingSlotLocationRepository locationRepository;

	@InjectMocks
	private ParkingSlotService parkingSlotService;

	/**
	 * Test for {@link ParkingSlotService#getAllAvailalbleParkingSlots()}
	 */
	@Test
	public void testGetAllParkingSlots() {
		List<ParkingSlot> parkingSlotList = getParkingSlotList();
		when(parkingSlotRepository.getAvailableParkingSlots()).thenReturn(parkingSlotList);
		List<ParkingSlot> allParkingSlots = parkingSlotService.getAllAvailalbleParkingSlots();
		assertNotNull(allParkingSlots);
		assertEquals(1, allParkingSlots.size());
	}

	/**
	 * Test for {@link ParkingSlotService#getAllAvailalbleParkingSlots(List))}
	 */
	@Test
	public void testGetAllParkingSlotsWithUniqueIds() {
		LocationSearchDTO locationSearchDTO = new LocationSearchDTO();
		List<ParkingSlotLocation> parkingSlotLocations = new ArrayList<>();
		List<ParkingSlot> parkingSlotList = getParkingSlotList();
		List<String> asList = new ArrayList<String>();
		when(locationRepository.findByPositionNear(new Point(1.0, 1.0), new Distance(10, Metrics.KILOMETERS))).thenReturn(parkingSlotLocations);
		when(parkingSlotRepository.getAvailableParkingSlots(asList)).thenReturn(parkingSlotList);
		List<ParkingSlot> allParkingSlots = parkingSlotService.getAllAvailalbleParkingSlots(locationSearchDTO);
		assertNotNull(allParkingSlots);
		assertEquals(1, allParkingSlots.size());
	}

	/**
	 * Test for {@link ParkingSlotService#reserveParkingSlot(long, long)()}
	 */
	@Test
	public void testReserveParkingSlot() {
		parkingSlotService.reserveParkingSlot(1L, 1L);
		verify(reservationRepository, times(1)).save(any(Reservation.class));
	}

	private List<ParkingSlot> getParkingSlotList() {
		List<ParkingSlot> parkingSlotList = new ArrayList<ParkingSlot>();
		ParkingSlot slot1 = new ParkingSlot();
		slot1.setId(1);
		slot1.setCostPerHour(100);
		slot1.setLatitude(79.3342345);
		slot1.setLogitude(33.4545667);
		parkingSlotList.add(slot1);
		return parkingSlotList;
	}
}
