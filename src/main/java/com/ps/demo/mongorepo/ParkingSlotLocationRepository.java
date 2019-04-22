package com.ps.demo.mongorepo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ps.demo.entities.ParkingSlotLocation;

@Repository
public interface ParkingSlotLocationRepository extends MongoRepository<ParkingSlotLocation, ObjectId>{

	List<ParkingSlotLocation> findByPositionNear(Point p, Distance d);
}
