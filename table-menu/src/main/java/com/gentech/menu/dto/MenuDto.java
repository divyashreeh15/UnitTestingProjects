package com.gentech.menu.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuDto {
	private Long id;
	private String name;
	private String description;
	private String category;
	private Long price;
	private Date createdAt;
	private Date updatedAt;

}
