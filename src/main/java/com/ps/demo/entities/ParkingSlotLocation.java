package com.ps.demo.entities;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="location")
public class ParkingSlotLocation {
	@Id
	private ObjectId _id;
	private double[] position;
	private String uniqueId;
}

