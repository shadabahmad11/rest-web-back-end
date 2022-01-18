package com.rest.restweb.common;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelRequest {
	
	private String hotelName;
	private String city;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
//	private int rooms;
//	private int adult;
//	private int child;
//	private int infant;
}
