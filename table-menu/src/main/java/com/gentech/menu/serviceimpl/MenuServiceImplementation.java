package com.gentech.menu.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gentech.menu.dto.MenuDto;
import com.gentech.menu.entity.Menu;
import com.gentech.menu.exception.ResourceNotFoundException;
import com.gentech.menu.mapper.MenuMapper;
import com.gentech.menu.repository.MenuRepository;
import com.gentech.menu.service.MenuService;

@Service
public class MenuServiceImplementation implements MenuService{
	@Autowired
	private MenuRepository menuRepo;

	@Override
	public List<MenuDto> getAllItems() {
		List<Menu> menu=menuRepo.findAll();
		List<MenuDto> menuDto= new ArrayList<>();
		for(int i=0;i<menu.size();i++)
		{
			menuDto.add(MenuMapper.MenuToMenuDto(menu.get(i)));
		}
		return menuDto;
	}

	@Override
	public MenuDto postItem(MenuDto dto) {
		Menu obj = MenuMapper.MenuDtoToMenu(dto);
		menuRepo.save(obj);
		dto=MenuMapper.MenuToMenuDto(obj);
		return dto;
	}

	@Override
	public MenuDto updateItem(MenuDto dto, Long id) {
		Menu menu=menuRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Menu Item", "Id", id));
		menu.setName(dto.getName());
		menu.setCategory(dto.getCategory());
		menu.setDescription(dto.getDescription());
		menu.setPrice(dto.getPrice());
		dto=MenuMapper.MenuToMenuDto(menu);
		return dto;
	}

	@Override
	public MenuDto getById(Long id) {
		Menu menu= menuRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Menu Item", "Id", id));
		MenuDto dto = MenuMapper.MenuToMenuDto(menu);
		return dto;
	}

	@Override
	public void deleteItemById(Long id) {
		menuRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Menu Item", "Id", id));
		menuRepo.deleteById(id);
	}

	@Override
	public List<MenuDto> getByName(String name) {
		List<MenuDto> menudto =new ArrayList<>();
		List<Menu> menu=menuRepo.findByName(name);
		for(int i=0;i<menu.size();i++)
		{
			menudto.add(MenuMapper.MenuToMenuDto(menu.get(i)));
		}
		
		return menudto;
	}

	@Override
	public List<MenuDto> getByDesc(String desec) {
		List<MenuDto> menudto =new ArrayList<>();
		List<Menu> menu=menuRepo.findByDescription(desec);
		for(int i=0;i<menu.size();i++)
		{
			menudto.add(MenuMapper.MenuToMenuDto(menu.get(i)));
		}
		
		return menudto;
	}

	@Override
	public List<MenuDto> getByCategory(String category) {
		List<MenuDto> menudto =new ArrayList<>();
		List<Menu> menu=menuRepo.findByCategory(category);
		for(int i=0;i<menu.size();i++)
		{
			menudto.add(MenuMapper.MenuToMenuDto(menu.get(i)));
		}
		
		return menudto;
	}

	@Override
	public List<MenuDto> getByPrice(Long price) {
		List<MenuDto> menudto =new ArrayList<>();
		List<Menu> menu=menuRepo.findByPrice(price);
		for(int i=0;i<menu.size();i++)
		{
			menudto.add(MenuMapper.MenuToMenuDto(menu.get(i)));
		}
		
		return menudto;
	}

	
	//Partial Matching
	@Override
	public List<MenuDto> getByPartialName(String val) {
	List<Menu> list = menuRepo.findByNameContaining(val);
	List<MenuDto> menudto=new ArrayList<>();
	for(int i=0;i<list.size();i++)
	{
		menudto.add(MenuMapper.MenuToMenuDto(list.get(i)));
	}
	return menudto ;
	}

	@Override
	public List<MenuDto> getByPageNoAndPageSize(int pageNo, int pageSize) {
		Pageable pages = Pageable.ofSize(pageSize);
		List<Menu> list  = menuRepo.findAll(pages).getContent();
		List<MenuDto> menudto=new ArrayList<>();
		for(int i=0;i<list.size();i++)
		{
			menudto.add(MenuMapper.MenuToMenuDto(list.get(i)));
		}
		return menudto ;
	}

	@Override
	public List<MenuDto> getByPageNoAndPageSizeSorted(int pageNo, int pageSize, String columnName) {
		Sort sort = Sort.by(Direction.DESC,columnName);
	Pageable page=PageRequest.of(pageNo, pageSize, sort);
	List<Menu> list  = menuRepo.findAll(page).getContent();
	List<MenuDto> menudto=new ArrayList<>();
	for(int i=0;i<list.size();i++)
	{
		menudto.add(MenuMapper.MenuToMenuDto(list.get(i)));
	}
	return menudto ;
		
	}
	
	
	

	

	
}
