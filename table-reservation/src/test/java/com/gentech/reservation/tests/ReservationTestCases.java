package com.gentech.reservation.tests;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Date;
import java.util.List;

import com.gentech.reservation.entity.Reservation;
import com.gentech.reservation.repository.ReservationRepository;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationTestCases {
@LocalServerPort
private int port;

private String URL = "http://localhost";
private static RestTemplate resttemplate;

@Autowired
private ReservationRepository resrepo;

@BeforeAll
public static void intialize()
{
	resttemplate=new RestTemplate();
	
}

@BeforeEach
public void setUp()
{
	URL = URL +":"+port+"/reservation/api";
}

@Test
public void shouldPostReservation()
{
	Reservation res1=new Reservation();
	res1.setName("Ananda");
	res1.setEvent("dance dance");
	res1.setPhoneNo("000000000");
	res1.setDateOfRes(Date.valueOf("2024-11-22"));
	
	
	Reservation postObj = resttemplate.postForObject(URL+"/post", res1, Reservation.class);
	assertNotNull(postObj);
	assertThat(postObj.getId()).isNotNull();
	assertEquals("Ananda",postObj.getName());
	resrepo.deleteById(postObj.getId());
	
}

@Test
public void shouldGetAllReservations()
{
	List<Reservation> list = resttemplate.getForObject(URL+"/all",List.class);
	assertNotNull(list);
	
}


@Test
public void shouldGetReservationById()
{
	Reservation res1=new Reservation();
	res1.setName("Ananda");
	res1.setEvent("dance dance");
	res1.setPhoneNo("000000000");
	res1.setDateOfRes(Date.valueOf("2024-11-22"));
	Reservation postObj = resttemplate.postForObject(URL+"/post", res1, Reservation.class);

	Reservation getObj = resttemplate.getForObject(URL+"/one/"+postObj.getId(), Reservation.class);
	assertNotNull(getObj);
	assertEquals(postObj.getId(),postObj.getId());
	resrepo.deleteById(getObj.getId());
}


@Test
public void shouldUpdateBasedOnId()
{
	
	Reservation res1=new Reservation();
	res1.setName("Ananda");
	res1.setEvent("dance dance");
	res1.setPhoneNo("000000000");
	res1.setDateOfRes(Date.valueOf("2024-11-22"));
	Reservation postObj = resttemplate.postForObject(URL+"/post", res1, Reservation.class);
	
	postObj.setName("Kumar");
	 resttemplate.put(URL+"/updateById/"+postObj.getId(), postObj, Reservation.class);
	 
	 assertEquals("Kumar",postObj.getName());
	 resrepo.deleteById(postObj.getId());
	 
}


@Test
public void shouldDeleteById()
{
	int sizeBefore=resttemplate.getForObject(URL+"/all",List.class).size();
	Reservation res1=new Reservation();
	res1.setName("Ananda");
	res1.setEvent("dance dance");
	res1.setPhoneNo("000000000");
	res1.setDateOfRes(Date.valueOf("2024-11-22"));
	Reservation postObj = resttemplate.postForObject(URL+"/post", res1, Reservation.class);
	
	
	
	
	resttemplate.delete(URL+"/deleteById/"+postObj.getId(), Reservation.class);
	int sizeAfter=resttemplate.getForObject(URL+"/all",List.class).size();
	assertEquals(sizeBefore, sizeAfter);
	
}




}
