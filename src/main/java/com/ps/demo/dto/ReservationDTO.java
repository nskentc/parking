package com.ps.demo.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ReservationDTO {
	private long id;
	private long parkingSlotId;
	private Date fromTime;
	private Date toTime;	
	private long vehicleOwnerId;
}
