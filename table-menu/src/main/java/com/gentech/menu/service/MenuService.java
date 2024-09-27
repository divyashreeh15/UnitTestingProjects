package com.gentech.menu.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.gentech.menu.dto.MenuDto;
import com.gentech.menu.entity.Menu;


public interface MenuService {
	List<MenuDto> getAllItems();
	MenuDto postItem(MenuDto dto);
	MenuDto updateItem(MenuDto dto,Long id );
	MenuDto getById(Long id);
	void deleteItemById(Long id);
	
	
	List<MenuDto> getByName(String name);
	List<MenuDto> getByDesc(String desec);
	List<MenuDto> getByCategory(String category);
	List<MenuDto> getByPrice(Long price);
	
	
	List<MenuDto> getByPartialName(String val);
	List<MenuDto> getByPageNoAndPageSize(int pageNo,int pageSize);
	List<MenuDto> getByPageNoAndPageSizeSorted(int pageNo,int pageSize,String columnName);
	
	
	
	

}
