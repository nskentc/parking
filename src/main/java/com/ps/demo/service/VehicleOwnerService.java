package com.ps.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.demo.dao.VehicleOwnerRepository;
import com.ps.demo.dto.RegisterVehicleOwnerDTO;
import com.ps.demo.dto.ValidationException;
import com.ps.demo.entities.VehicleOwner;

@Service
public class VehicleOwnerService {
	
	@Autowired
	private VehicleOwnerRepository vehicleOwnerRepository;
	
	@Autowired
	private UtilityService utililtyService;

	/**
	 * Register vehicle owner
	 * @param RegisterVehicleOwnerDTO
	 */
	public void register(RegisterVehicleOwnerDTO registerVehicleOwnerDTO) {
		boolean isValidPhoneNo = utililtyService.validatePhoneNo(registerVehicleOwnerDTO.getPhoneNo(), registerVehicleOwnerDTO.getCountryCode());
		if(!isValidPhoneNo) {
			throw new ValidationException("Phone Number not valid");
		}
		VehicleOwner vehicleOwner = new VehicleOwner();
		vehicleOwner.setName(registerVehicleOwnerDTO.getOwnerName());
		vehicleOwner.setPhoneNo(registerVehicleOwnerDTO.getPhoneNo());
		vehicleOwner.setVehicleNo(registerVehicleOwnerDTO.getVechicleNo());
		vehicleOwnerRepository.save(vehicleOwner);
	}
}
