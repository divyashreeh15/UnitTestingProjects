package com.gentech.menu.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import com.gentech.menu.entity.Menu;

public interface MenuRepository  extends JpaRepository<Menu, Long>,PagingAndSortingRepository<Menu,Long>{
		List<Menu> findByName(String name);
		List<Menu> findByDescription(String desec);
		List<Menu> findByCategory(String category);
		List<Menu> findByPrice(Long price);
		
		//Partial
		List<Menu> findByNameContaining(String val);
		
		
}
