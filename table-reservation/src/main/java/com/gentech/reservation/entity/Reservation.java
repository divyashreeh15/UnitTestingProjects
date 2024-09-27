package com.gentech.reservation.entity;

import java.util.Date;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="reservation_tbl")
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="reservation_id")
	private Long id;
	
	@Column(name="reservation_under")
	private String name;
	
	@Column(name="event_type")
	private String event;
	
	@Column(name="phone_no")
	private String phoneNo;
	
	@Column(name="dateOfRes")
	private  java.sql.Date dateOfRes;
	
	@CreationTimestamp
	@Column(name="created_at",nullable = false,updatable = false)
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at",nullable = false)
	private Date updatedAt;
	
	
}
