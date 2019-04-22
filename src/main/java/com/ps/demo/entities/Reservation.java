package com.ps.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Reservation {
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne
	private ParkingSlot parkingSlot;
	private Date fromTime;
	private Date toTime;	
	@ManyToOne
	private VehicleOwner vehicleOwner;
}
