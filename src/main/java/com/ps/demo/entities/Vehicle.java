package com.ps.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Vehicle {
	@Id
	@GeneratedValue
	private long id;
	
	private String registrationNo;
}
