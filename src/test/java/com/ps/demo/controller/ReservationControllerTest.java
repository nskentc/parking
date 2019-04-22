package com.ps.demo.controller;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ps.demo.dto.ReservationDTO;
import com.ps.demo.service.ReservationService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ReservationControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private ReservationService reservationService;

	/**
	 * Test for {@link ReservationController#getReservation(long)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetReservation() throws Exception {
		ReservationDTO reservationDTO = new ReservationDTO();
		reservationDTO.setId(1);

		when(reservationService.getReservation(1)).thenReturn(reservationDTO);

		mvc.perform(get("/reservations/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)));
	}

	/**
	 * Test for {@link ReservationController#getReservationCost(long)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetReservationCost() throws Exception {
		when(reservationService.getReservationCost(1)).thenReturn(20L);

		mvc.perform(get("/reservations/1/cost").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(20)));
	}

	/**
	 * Test for {@link ReservationController#cancelReservation(long)}
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCancelReservation() throws Exception {
		when(reservationService.getReservationCost(1)).thenReturn(20L);

		mvc.perform(delete("/reservations/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		verify(reservationService).cancelReservation(1L);
	}
}
