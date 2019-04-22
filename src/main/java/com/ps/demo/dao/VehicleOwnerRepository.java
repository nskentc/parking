package com.ps.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ps.demo.entities.VehicleOwner;

@Repository
public interface VehicleOwnerRepository extends JpaRepository<VehicleOwner, Long>{

}
