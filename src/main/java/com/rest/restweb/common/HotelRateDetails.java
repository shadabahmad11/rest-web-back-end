package com.rest.restweb.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelRateDetails {

	private String type;
	private String room;
	private int remaining;
	private String rate;
	private String rate_desc;
	private double price;
	private double retail;
	private double discount;
	
}
