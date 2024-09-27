package com.gentech.reservation.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gentech.reservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>,PagingAndSortingRepository<Reservation, Long> {
	List<Reservation> findByNameIgnoreCase(String name);
	List<Reservation> findByEventIgnoreCase(String event);
	List<Reservation> findByDateOfRes(Date date);
	List<Reservation> findByPhoneNo(String no);
	
	//Like
	List<Reservation> findByNameContaining(String val);
	
	@Transactional
	@Modifying
	@Query("Update Reservation set phoneNo=:phoneNum where name=:nameOf")
	Integer updatePhoneNoByName(@Param("phoneNum") String phoneNum,@Param("nameOf") String nameOf);
		
	
	

}
