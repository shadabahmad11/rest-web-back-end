package com.rest.restweb.service;

import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rest.restweb.common.HotelDetailResponse;
import com.rest.restweb.common.HotelDetails;
import com.rest.restweb.common.HotelRequest;
import com.rest.restweb.common.HotelResponse;
import com.rest.restweb.entity.Hotel;
import com.rest.restweb.repository.HotelRepository;

@Service
public class HotelService {
	
	@Autowired
	private HotelRepository hotelRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${rest-web.hotels.url}")
	private String hotelsURL;
	
	@Value("${rest-web.hotels.basicUserName}")
	private String basicUserName;
	
	@Value("${rest-web.hotels.basicPass}")
	private String basicPass;
	
	public List<Hotel> saveHotelList(List<Hotel> hotelList) {
		return hotelRepository.saveAll(hotelList);
	}

	public List<Hotel> fetchHotelList() {
		return hotelRepository.findAll();
	}
	
	public Hotel fetchHotelDetails(int hotelId) {
		return hotelRepository.findByHotelId(hotelId);
	}
	
	/**
	 * Fetch the list of all hotels without applying any filter
	 * @return Object
	 */
	public Object fetchHotels() {
		String url = hotelsURL + "/room/SILVERCOVE";
		return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(getBasicAuthHeader()), Object.class);
	}
	
	/**
	 * Fetch the hotel list by applying some filters.
	 * @param hotelRequest
	 * @return
	 */
	public List<HotelDetails> searchHotels(HotelRequest hotelRequest) {
		StringBuilder hotelSearchSB = new StringBuilder("/availability?");
		if(hotelRequest != null) {
			if(StringUtils.isNotBlank(hotelRequest.getCity())) {
				hotelSearchSB.append("location=" + hotelRequest.getCity() + "&");
			}
			if(StringUtils.isNotBlank(hotelRequest.getHotelName())) {
				hotelSearchSB.append("name=" + hotelRequest.getHotelName() + "&");
			}
			if(hotelRequest.getCheckInDate() != null) {
				hotelSearchSB.append("checkin=" + hotelRequest.getCheckInDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "&");
			}
			if(hotelRequest.getCheckOutDate() != null) {
				hotelSearchSB.append("checkout=" + hotelRequest.getCheckOutDate().format(DateTimeFormatter.ISO_LOCAL_DATE) + "&");
			}
		}
		String url = hotelsURL + hotelSearchSB.toString();
		ResponseEntity<HotelResponse> hotels = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(getBasicAuthHeader()), HotelResponse.class);
		return hotels.getBody().getData().getHotels();
	}
	
	/**
	 * Fetch the hotel list by applying some filters.
	 * @param hotelRequest
	 * @return
	 */
	public HotelDetails getHotelDetails(String hotelCode) {
		String url = hotelsURL + "/property/" + hotelCode;
		ResponseEntity<HotelDetailResponse> hotels = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<String>(getBasicAuthHeader()), HotelDetailResponse.class);
		return hotels.getBody().getData();
	}
	
	public HttpHeaders getBasicAuthHeader() {
		String basicAuth = basicUserName + ":" + basicPass;
		String base64Creds = new String(Base64.getEncoder().encode(basicAuth.getBytes()));

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + base64Creds);
		headers.add("Accept", "application/json");
		return headers;
	}
}
