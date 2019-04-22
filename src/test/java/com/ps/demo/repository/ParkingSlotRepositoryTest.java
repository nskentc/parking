package com.ps.demo.repository;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ps.demo.dao.ParkingSlotRepository;
import com.ps.demo.entities.ParkingSlot;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParkingSlotRepositoryTest {

	@Autowired
	private ParkingSlotRepository parkingSlotRepository;

	/**
	 * Test for {@link ParkingSlotRepository#getAvailableParkingSlots()}
	 */
	@Test
	public void testGetAvailableParkingSlots() {
		List<ParkingSlot> findAll = parkingSlotRepository.getAvailableParkingSlots();
		assertEquals(4, findAll.size());
	}

	/**
	 * Test for {@link ParkingSlotRepository#getAvailableParkingSlots(List)}
	 */
	@Test
	public void testGetAvailableParkingSlotsWithParam() {
		List<String> uniqueLocationIds = new ArrayList<>();
		uniqueLocationIds.add("1");
		List<ParkingSlot> findAll = parkingSlotRepository.getAvailableParkingSlots(uniqueLocationIds);
		assertEquals(1, findAll.size());
	}
}
