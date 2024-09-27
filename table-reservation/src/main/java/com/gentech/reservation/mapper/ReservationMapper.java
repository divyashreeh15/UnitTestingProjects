package com.gentech.reservation.mapper;

import com.gentech.reservation.dto.ReservationDto;
import com.gentech.reservation.entity.Reservation;

public class ReservationMapper {
	public static Reservation mapResDtoToRes(ReservationDto resDto)
	{
		Reservation res=new Reservation(
				resDto.getId(),
				resDto.getName(),
				resDto.getEvent(),
				resDto.getPhoneNo(),
				resDto.getDateOfRes(),
				resDto.getCreatedAt(),
				resDto.getUpdatedAt());
		return res;
	}
	
	public static ReservationDto mapResToResDto(Reservation res)
	{
		ReservationDto resDto=new ReservationDto(
				res.getId(),
				res.getName(),
				res.getEvent(),
				res.getPhoneNo(),
				res.getDateOfRes(),
				res.getCreatedAt(),
				res.getUpdatedAt());
		return resDto;
	}

}
