package com.gentech.reservation.serviceimpl;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gentech.reservation.dto.ReservationDto;
import com.gentech.reservation.entity.Reservation;
import com.gentech.reservation.exception.ResourceNotFoundException;
import com.gentech.reservation.mapper.ReservationMapper;
import com.gentech.reservation.repository.ReservationRepository;
import com.gentech.reservation.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	private ReservationRepository reservationRepo;
	
	//Post
	@Override
	public ReservationDto postItem(ReservationDto resDto) {
		Reservation newObject = ReservationMapper.mapResDtoToRes(resDto);
		reservationRepo.save(newObject);
		resDto = ReservationMapper.mapResToResDto(newObject);
		return resDto;
	}
	
	//GetAll
	@Override
	public List<ReservationDto> getListOfItems() {
		List<Reservation> res=reservationRepo.findAll();
		List<ReservationDto> resDto =new ArrayList<>();
		for(int i=0;i<res.size();i++)
		{
			resDto.add(ReservationMapper.mapResToResDto(res.get(i)));
		}
		return resDto;
	}

	//Get Particular By Id
	@Override
	public ReservationDto getByParticularId(Long id) {
		Reservation existingOb = reservationRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Reservation","Id",id));
		ReservationDto resDto = ReservationMapper.mapResToResDto(existingOb);
		return resDto;
	}

	//Modify Item
	@Override
	public ReservationDto updateItem(Long id, ReservationDto resDto) {
		Reservation existingOb = reservationRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Reservation","Id",id));
		existingOb.setName(resDto.getName());
		existingOb.setEvent(resDto.getEvent());
		existingOb.setPhoneNo(resDto.getPhoneNo());
		existingOb.setDateOfRes(resDto.getDateOfRes());
		reservationRepo.save(existingOb);
		resDto = ReservationMapper.mapResToResDto(existingOb);
		return resDto;
	}

	
	
	//Delete Item
	@Override
	public void deleteItemById(Long id) {
		reservationRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Reservation", "Id", id));
		reservationRepo.deleteById(id);
	
	}

	//Reservation By name
	@Override
	public List<ReservationDto> getResByName(String name) {
		List<Reservation> res=reservationRepo.findByNameIgnoreCase(name);
		List<ReservationDto> resDto =new ArrayList<>();
		for(int i=0;i<res.size();i++)
		{
			resDto.add(ReservationMapper.mapResToResDto(res.get(i)));
		}
		
		return resDto;
	}
	
	//Reservation By event
	@Override
	public List<ReservationDto> getResByEvent(String event) {
		List<Reservation> res=reservationRepo.findByEventIgnoreCase(event);
		List<ReservationDto> resDto =new ArrayList<>();
		for(int i=0;i<res.size();i++)
		{
			resDto.add(ReservationMapper.mapResToResDto(res.get(i)));
		}
		
		return resDto;
	}

	@Override
	public List<ReservationDto> getResByDate(Date date) {
		
		List<Reservation> res=reservationRepo.findByDateOfRes(date);
		List<ReservationDto> resDto =new ArrayList<>();
		for(int i=0;i<res.size();i++)
		{
			resDto.add(ReservationMapper.mapResToResDto(res.get(i)));
		}
		
		return resDto;
	}

	@Override
	public List<ReservationDto> getResByPhone(String no) {
		List<Reservation> res=reservationRepo.findByPhoneNo(no);
		List<ReservationDto> resDto =new ArrayList<>();
		for(int i=0;i<res.size();i++)
		{
			resDto.add(ReservationMapper.mapResToResDto(res.get(i)));
		}
		
		return resDto;
	}

	//Partial Matching
	@Override
	public List<ReservationDto> getPartialResByName(String val) {
		List<Reservation> list=reservationRepo.findByNameContaining(val);
		List<ReservationDto> resDto =new ArrayList<>();
		for(int i=0;i<list.size();i++)
		{
			resDto.add(ReservationMapper.mapResToResDto(list.get(i)));
		}
		
		return resDto;
		
	}

	@Override
	public List<ReservationDto> getResByPaging(int pageNo, int pageSize) {
		Pageable page=PageRequest.of(pageNo, pageSize);
		return reservationRepo.findAll(page).stream().map((Reservation)->ReservationMapper.mapResToResDto(Reservation)).collect(Collectors.toList());
	}
	
	
	@Override
	public List<ReservationDto> getResByPagingAndSorting(int pageNo, int pageSize,String colName) {
		Sort sort=Sort.by(Direction.ASC,colName);
		Pageable page=PageRequest.of(pageNo, pageSize,sort);
		return reservationRepo.findAll(page).stream().map((Reservation)->ReservationMapper.mapResToResDto(Reservation)).collect(Collectors.toList());
	}

	@Override
	public Integer getNameUpdatePhone(String Phone, String name) {
		return reservationRepo.updatePhoneNoByName(Phone, name);
	}
	
	
	
	
	
	

}
