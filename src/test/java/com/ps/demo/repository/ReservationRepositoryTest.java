package com.ps.demo.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ps.demo.dao.ReservationRepository;
import com.ps.demo.entities.Reservation;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationRepositoryTest {
	
	@Autowired
	private ReservationRepository reservationRepository;

	/**
	 * Test for {@link ReservationRepository#findAll()}
	 */
	@Test
	public void testFindAll() throws Exception {
		List<Reservation> findAll = reservationRepository.findAll();
		assertEquals(1, findAll.size());
	}

}
