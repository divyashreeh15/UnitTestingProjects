package com.gentech.menu.controller;

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

import com.gentech.menu.dto.MenuDto;
import com.gentech.menu.service.MenuService;

@RestController
@RequestMapping("/apimenu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	
	@PostMapping("/postMenu")
	public ResponseEntity<MenuDto> add(@RequestBody MenuDto dto)
	{
		return new ResponseEntity<MenuDto>(menuService.postItem(dto),HttpStatusCode.valueOf(201));
	}
	
	@GetMapping("/getMenu")
	public ResponseEntity<List<MenuDto>> getALLIt()
	{
		return new ResponseEntity<List<MenuDto>>(menuService.getAllItems(),HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getMenyById/{id}")
	public ResponseEntity<MenuDto> getMenuByid(@PathVariable Long id)
	{
		return new ResponseEntity<MenuDto>(menuService.getById(id),HttpStatusCode.valueOf(200));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<MenuDto> updateByid(@RequestBody MenuDto menudto,@PathVariable Long id)
	{
		return new ResponseEntity<MenuDto>(menuService.updateItem(menudto,id),HttpStatusCode.valueOf(200));
	}
	
	@DeleteMapping("/deleteMenu/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable Long id)
	{
		menuService.deleteItemById(id);
		return new ResponseEntity<String>("Menu item with Id "+id+" was successfully deleted",HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getMenuByName")
	public ResponseEntity<List<MenuDto>> getFromName(@RequestParam String name)
	{
		return new ResponseEntity<List<MenuDto>>(menuService.getByName(name),HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getMenuByDesc")
	public ResponseEntity<List<MenuDto>> getFromDesc(@RequestParam String name)
	{
		return new ResponseEntity<List<MenuDto>>(menuService.getByDesc(name),HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getMenuByCategory")
	public ResponseEntity<List<MenuDto>> getFromCate(@RequestParam String name)
	{
		return new ResponseEntity<List<MenuDto>>(menuService.getByCategory(name),HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getMenuByPrice")
	public ResponseEntity<List<MenuDto>> getFromCate(@RequestParam Long name)
	{
		return new ResponseEntity<List<MenuDto>>(menuService.getByPrice(name),HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getByPartial")
	public ResponseEntity<List<MenuDto>> getPartial(@RequestParam String val)
	{
		return new ResponseEntity<List<MenuDto>>(menuService.getByPartialName(val),HttpStatusCode.valueOf(200));
	}
	
	
	@GetMapping("/getByPageNo&Size")
	public ResponseEntity<List<MenuDto>> getFromPgNo(@RequestParam int pageNo,@RequestParam int pageSize)
	{
		return new ResponseEntity<List<MenuDto>>(menuService.getByPageNoAndPageSize(pageNo,pageSize),HttpStatusCode.valueOf(200));
	}
	
	@GetMapping("/getByPageNo&Size&Sort")
	public ResponseEntity<List<MenuDto>> getFromPgNoSorted(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String columnName)
	{
		return new ResponseEntity<List<MenuDto>>(menuService.getByPageNoAndPageSizeSorted(pageNo,pageSize,columnName),HttpStatusCode.valueOf(200));
	}
	
	
 
}
