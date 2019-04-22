package com.ps.demo.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ps.demo.dao.VehicleOwnerRepository;
import com.ps.demo.entities.VehicleOwner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleOwnerRepositoryTest {
	
	@Autowired
	private VehicleOwnerRepository vehicleOwnerRepository;

	/**
	 * Test for {@link VehicleOwnerRepository#findAll()}
	 */
	@Test
	public void testFindAll() throws Exception {
		List<VehicleOwner> findAll = vehicleOwnerRepository.findAll();
		assertEquals(4, findAll.size());
	}
}
