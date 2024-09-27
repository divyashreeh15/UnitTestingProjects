package com.gentech.reservation.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
	private Long id;
	private String name;
	private String event;
	private String phoneNo;
	private  java.sql.Date dateOfRes;
	private Date createdAt;
	private Date updatedAt;

}
