package com.rest.restweb.common;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDetails {

	private String code;
	private String name;
	private int rating;
    private String currency;
    private double minprice;
    private LocationDetails location;
    private String type;
    private List<HotelRateDetails> rates;
    private List<HotelRoom> rooms;
}
