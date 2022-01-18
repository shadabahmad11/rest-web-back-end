package com.rest.restweb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {
	
	@Id
	@GeneratedValue
	private int hotelId;
	private String hotelName;
	private String city;
	private int availableRoomCount;
	private int personCount;
	private double lowestPricePerNight;
	private String facilities;
	
}
