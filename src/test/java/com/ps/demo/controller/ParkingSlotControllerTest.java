package com.ps.demo.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.demo.dto.LocationSearchDTO;
import com.ps.demo.entities.ParkingSlot;
import com.ps.demo.service.ParkingSlotService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingSlotControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private ParkingSlotService parkingSlotService;

	/**
	 * Test for {@link ParkingSlotController#getAllAvailalbleParkingSlots()}
	 * @throws Exception 
	 * 
	 */
	@Test
	public void testGetAllParkingSlots() throws Exception {
		ParkingSlot slot = new ParkingSlot();

		List<ParkingSlot> slots = Arrays.asList(slot);

		when(parkingSlotService.getAllAvailalbleParkingSlots()).thenReturn(slots);

		mvc.perform(get("/parkingSlots/available").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
	}

	/**
	 * Test for {@link ParkingSlotController#searchParkingSlots(LocationSearchDTO)}
	 * @throws Exception
	 */
	@Test
	public void testSearchParkingSlots() throws Exception {
		LocationSearchDTO locationSearchDTO = new LocationSearchDTO();
		ParkingSlot slot = new ParkingSlot();
		List<ParkingSlot> slots = Arrays.asList(slot);
		String locationJSON = new ObjectMapper().writeValueAsString(locationSearchDTO);

		when(parkingSlotService.getAllAvailalbleParkingSlots(locationSearchDTO)).thenReturn(slots);

		mvc.perform(post("/parkingSlots/searchAvailable").content(locationJSON).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
	}

	/**
	 * Test for {@link ParkingSlotController#reserveParkingSlot(long, long)}
	 * @throws Exception
	 */
	@Test
	public void testReserveParkingSlot() throws Exception {
		mvc.perform(post("/parkingSlots/1/reservations/vehicleOwner/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		verify(parkingSlotService).reserveParkingSlot(1L,1L);
	}
	

}
