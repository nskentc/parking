package com.ps.demo.controller;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.demo.dto.RegisterVehicleOwnerDTO;
import com.ps.demo.service.VehicleOwnerService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class VehicleOwnerControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private VehicleOwnerService vehicleOwnerService;

	@Test
	public void testSearchParkingSlots() throws Exception {
		RegisterVehicleOwnerDTO registerVehicleOwnerDTO = new RegisterVehicleOwnerDTO();
		String registrationJSON = new ObjectMapper().writeValueAsString(registerVehicleOwnerDTO);
		mvc.perform(post("/vehicleOwners").contentType(MediaType.APPLICATION_JSON).content(registrationJSON))
				.andExpect(status().isCreated());
		verify(vehicleOwnerService).register(registerVehicleOwnerDTO);
		
		mvc.perform(post("/vehicleOwners").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isUnprocessableEntity());
	}
}
