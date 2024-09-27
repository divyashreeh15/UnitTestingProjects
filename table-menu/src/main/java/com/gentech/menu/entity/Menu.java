package com.gentech.menu.entity;

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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="tbl_menu")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="nameOfItem")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="categoryOfItem")
	private String category;
	
	@Column(name="priceOfItem")
	private Long price;
		
	@CreationTimestamp
	@Column(name="created_at",nullable = false,updatable = false)
	private Date createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at",nullable = false)
	private Date updatedAt;
	
	
}
