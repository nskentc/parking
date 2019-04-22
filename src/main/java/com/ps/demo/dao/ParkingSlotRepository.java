package com.ps.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ps.demo.entities.ParkingSlot;

@Repository
public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long>{

	@Query("select ps from ParkingSlot ps where ps.id not in(select reservation.parkingSlot.id from Reservation reservation where reservation.toTime is null)")
	public List<ParkingSlot> getAvailableParkingSlots();
	

	@Query("select ps from ParkingSlot ps where ps.uniqueId in :uniqueIds and ps.id not in(select reservation.parkingSlot.id from Reservation reservation where reservation.toTime is null)")
	public List<ParkingSlot> getAvailableParkingSlots(@Param("uniqueIds") List<String> uniqueLocationIds);
}
