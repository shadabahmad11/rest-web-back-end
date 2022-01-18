package com.rest.restweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.restweb.common.HotelDetails;
import com.rest.restweb.common.HotelRequest;
import com.rest.restweb.service.HotelService;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@GetMapping("/fetchHotels")
	public Object fetchHotels() {
		return hotelService.fetchHotels();
	}
	
	@PostMapping("/searchHotels")
	public Object searchHotels(@RequestBody HotelRequest hotelRequest) {
		return hotelService.searchHotels(hotelRequest);
	}
	
	@GetMapping("/getHotelDetails/{hotelCode}")
	public HotelDetails getHotelDetails(@PathVariable String hotelCode) {
		return hotelService.getHotelDetails(hotelCode);
	}
}
