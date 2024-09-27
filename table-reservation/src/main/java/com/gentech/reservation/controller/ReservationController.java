package com.gentech.reservation.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gentech.reservation.dto.ReservationDto;
import com.gentech.reservation.entity.Reservation;
import com.gentech.reservation.service.ReservationService;

//  http://localhost:9093/reservation/api
@RestController
@RequestMapping("/reservation/api")
public class ReservationController {
	@Autowired
	private ReservationService resService;
	
	@PostMapping("/post")
	public ResponseEntity<ReservationDto> addObject(@RequestBody ReservationDto resDto)
	{
		return new ResponseEntity<ReservationDto>(resService.postItem(resDto),HttpStatusCode.valueOf(201));
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<ReservationDto>> getObjects()
	{
		return new ResponseEntity<List<ReservationDto>>(resService.getListOfItems(),HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<ReservationDto> getParticular(@PathVariable Long id)
	{
		return new ResponseEntity<ReservationDto>(resService.getByParticularId(id),HttpStatusCode.valueOf(200));
	}
	
	@PutMapping("/updateById/{id}")
	public ResponseEntity<ReservationDto> updateById(@PathVariable Long id,@RequestBody ReservationDto dto)
	{
		return new ResponseEntity<ReservationDto>(resService.updateItem(id, dto), HttpStatusCode.valueOf(200));
	}
	
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteItemById(@PathVariable Long id)
	{
		resService.deleteItemById(id);
		return new ResponseEntity<String>("Reservation No "+id + " was successfully deleted",HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getByName")
	public ResponseEntity<List<ReservationDto>> getReservation(@RequestParam String name)
	{
		return new ResponseEntity<List<ReservationDto>>(resService.getResByName(name),HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getByEvent")
	public ResponseEntity<List<ReservationDto>> getReservationByEvent(@RequestParam String event)
	{
		return new ResponseEntity<List<ReservationDto>>(resService.getResByEvent(event),HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getByDate")
	public ResponseEntity<List<ReservationDto>> getReservationByDate(@RequestParam Date date)
	{
		return new ResponseEntity<List<ReservationDto>>(resService.getResByDate(date),HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getByPhone")
	public ResponseEntity<List<ReservationDto>> getReservationByPhone(@RequestParam String no)
	{
		return new ResponseEntity<List<ReservationDto>>(resService.getResByPhone(no),HttpStatusCode.valueOf(200));
	}
	
	
	//{{reg}}/getPartialMatchingName?val=tha
	@GetMapping("/getPartialMatchingName")
	public ResponseEntity<List<ReservationDto>> getPartialMatchingName(@RequestParam String val)
	{
		return new ResponseEntity<List<ReservationDto>>(resService.getPartialResByName(val),HttpStatusCode.valueOf(200));
	}
	
	//{{reg}}/getPageAndSort?pageNo=0&pageRecord=2&val=id
		@GetMapping("/getPageAndSort")
		public ResponseEntity<List<ReservationDto>> getPageAndSort(@RequestParam int pageNo,@RequestParam int pageRecord,String val)
		{
			return new ResponseEntity<List<ReservationDto>>(resService.getResByPagingAndSorting(pageNo,pageRecord,val),HttpStatusCode.valueOf(200));
		}
	//{{reg}}/UpdateByPhone?phoneNo=22222222&name=Anjali
		@PutMapping("/UpdateByPhone")
		public ResponseEntity<String> getPhone(@RequestParam String phoneNo,@RequestParam String name)
		{
			Integer count =resService.getNameUpdatePhone(phoneNo,name);
			return new ResponseEntity<String>(count+" Values updated",HttpStatusCode.valueOf(200));
		}
	
	
	
}
