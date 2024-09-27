package com.gentech.menu.testcases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import com.gentech.menu.entity.Menu;
import com.gentech.menu.repository.MenuRepository;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MenuTestCases {
	
	@LocalServerPort
	private int port;
	
	private String URL = "http://localhost";
	
	@Autowired
	private MenuRepository menuRepo;
	
	private static RestTemplate restTemplate;
	
	@BeforeAll
	public static void inti()
	{
		restTemplate = new RestTemplate();
	}
	@BeforeEach()
	public  void setUp()
	{
		URL = URL+":"+port+"/apimenu/";
	}
	
	
	@Test
	public void shouldPostMenu()
	{
		Menu obj = new Menu();
		obj.setName("Black Coffe");
		obj.setCategory("Drink");
		obj.setDescription("Mexican coffee with cream");
		obj.setPrice(Long.valueOf("899"));
		
		Menu returned = restTemplate.postForObject(URL+"postMenu", obj, Menu.class);
		
		assertNotNull(returned);
		menuRepo.deleteById(returned.getId());
		
	}
	
	@Test
	public void shouldGetAllMenu()
	{
		Menu obj = new Menu();
		obj.setName("Black Coffe");
		obj.setCategory("Drink");
		obj.setDescription("Mexican coffee with cream");
		obj.setPrice(Long.valueOf("899"));
		
		Menu obj2 = new Menu();
		obj2.setName("Red Velvet Cake");
		obj2.setCategory("Cakes");
		obj2.setDescription("Belgian cake with cream");
		obj2.setPrice(Long.valueOf("1012"));
		
		Menu returned = restTemplate.postForObject(URL+"postMenu", obj, Menu.class);
		Menu returned2 = restTemplate.postForObject(URL+"postMenu", obj2, Menu.class);
		assertNotNull(returned);
		assertNotNull(returned2);
		
		List<Menu> list = restTemplate.getForObject(URL+"getMenu", List.class);	
		assertNotNull(list);
		
		menuRepo.deleteById(returned.getId());
		menuRepo.deleteById(returned2.getId());
		
	}
	
	
	
	@Test
	public void shouldGetById()
	{
		Menu obj = new Menu();
		obj.setName("Black Coffe");
		obj.setCategory("Drink");
		obj.setDescription("Mexican coffee with cream");
		obj.setPrice(Long.valueOf("899"));
		
		Menu obj2 = new Menu();
		obj2.setName("Red Velvet Cake");
		obj2.setCategory("Cakes");
		obj2.setDescription("Belgian cake with cream");
		obj2.setPrice(Long.valueOf("1012"));
		
		Menu returned = restTemplate.postForObject(URL+"postMenu", obj, Menu.class);
		Menu returned2 = restTemplate.postForObject(URL+"postMenu", obj2, Menu.class);
		assertNotNull(returned);
		assertNotNull(returned2);
		
		Menu ele = restTemplate.getForObject(URL+"getMenyById/"+returned.getId(),Menu.class);	
		assertNotNull(ele);
		assertEquals(returned.getName(), ele.getName());
		
		menuRepo.deleteById(returned.getId());
		menuRepo.deleteById(returned2.getId());
		
	}
	
	

	@Test
	public void shouldDeleteMenuById()
	{
		int sizeBefore = restTemplate.getForObject(URL+"getMenu", List.class).size();
		Menu obj = new Menu();
		obj.setName("Black Coffe");
		obj.setCategory("Drink");
		obj.setDescription("Mexican coffee with cream");
		obj.setPrice(Long.valueOf("899"));
		Menu returned = restTemplate.postForObject(URL+"postMenu", obj, Menu.class);
		
		 restTemplate.delete(URL+"deleteMenu/"+returned.getId());
		 int sizeAfter = restTemplate.getForObject(URL+"getMenu", List.class).size();
		assertEquals(sizeBefore, sizeAfter);
		
		
	}
	
	
	@Test
	public void shouldUpdateById()
	{
		
		Menu obj = new Menu();
		obj.setName("Black Coffe");
		obj.setCategory("Drink");
		obj.setDescription("Mexican coffee with cream");
		obj.setPrice(Long.valueOf("899"));
		Menu returned = restTemplate.postForObject(URL+"postMenu", obj, Menu.class);
			
		returned.setName("Pink coffe");
		restTemplate.put(URL+"update/"+returned.getId(),returned,Menu.class);
		assertEquals("Pink coffe", returned.getName());
		
		menuRepo.deleteById(returned.getId());
	}
	
	
	
	

}
