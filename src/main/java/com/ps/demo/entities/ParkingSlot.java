package com.ps.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ParkingSlot {
	@Id
	@GeneratedValue
	private long id;
	private String uniqueId;
	private double logitude;
	private double latitude;
	private long costPerHour;
}
