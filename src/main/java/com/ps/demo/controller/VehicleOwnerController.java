package com.ps.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ps.demo.dto.RegisterVehicleOwnerDTO;
import com.ps.demo.service.VehicleOwnerService;

@RestController
@RequestMapping(path="vehicleOwners")
public class VehicleOwnerController {
	
	@Autowired
	private VehicleOwnerService vehicleOwnerService;
	
	/**
	 * Register vehicle owner.
	 * @param registerVehicleOwnerDTO
	 * @return response code 201 on success
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void register(@RequestBody RegisterVehicleOwnerDTO registerVehicleOwnerDTO) {
		vehicleOwnerService.register(registerVehicleOwnerDTO);
	}
}
