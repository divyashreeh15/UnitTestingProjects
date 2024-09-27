package com.gentech.reservation.service;

import java.sql.Date;
import java.util.List;

import com.gentech.reservation.dto.ReservationDto;
import com.gentech.reservation.entity.Reservation;

public interface ReservationService {

	ReservationDto postItem(ReservationDto resDto);
	List<ReservationDto> getListOfItems();
	ReservationDto getByParticularId(Long id);
	ReservationDto updateItem(Long id,ReservationDto resDto);
	void deleteItemById(Long id);
	
	
	//Finder Methods
	List<ReservationDto> getResByName(String name);
	List<ReservationDto> getResByEvent(String event);
	List<ReservationDto> getResByDate(Date date);
	List<ReservationDto> getResByPhone(String no);
	
	
	//Like Operator
	List<ReservationDto> getPartialResByName(String val);
	
	
	//Pagination
	List<ReservationDto> getResByPaging(int pageNo,int pageSize);
	
	
	List<ReservationDto> getResByPagingAndSorting(int pageNo,int pageSize,String colName);
	
	//Update
   Integer getNameUpdatePhone(String Phone,String name);
}
