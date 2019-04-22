package com.ps.demo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ps.demo.dao.VehicleOwnerRepository;
import com.ps.demo.dto.RegisterVehicleOwnerDTO;
import com.ps.demo.entities.VehicleOwner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleOwnerServiceTest {

	@Mock
	private VehicleOwnerRepository vehicleOwnerRepository;
	
	@Mock
	private UtilityService utililtyService;
	
	@InjectMocks
	private VehicleOwnerService vehicleOwnerService;	
	
	/**
	 * Test for {@link VehicleOwnerService#register(com.ps.demo.dto.RegisterVehicleOwnerDTO))}
	 */
	@Test
	public void testRegister() {
		RegisterVehicleOwnerDTO registerVehicleOwnerDTO = new RegisterVehicleOwnerDTO();
		String countryCode="IN";
		String phoneNo="98989898989";
		registerVehicleOwnerDTO.setCountryCode(countryCode);
		registerVehicleOwnerDTO.setPhoneNo(phoneNo);
		when(utililtyService.validatePhoneNo(phoneNo, countryCode)).thenReturn(Boolean.TRUE);
		vehicleOwnerService.register(registerVehicleOwnerDTO);
		verify(vehicleOwnerRepository, times(1)).save(any(VehicleOwner.class));
	}
}
