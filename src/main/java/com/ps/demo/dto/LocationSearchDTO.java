package com.ps.demo.dto;

import lombok.Data;

@Data
public class LocationSearchDTO {
	private double longitude;
	private double latitude;
	private int radius;
}
