package com.railwayBookingManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.railwayBookingManagement.DTO.BookingDTO;
import com.railwayBookingManagement.Exception.InvalidBookingException;

@Service
public interface BookingServiceI {
	
	public String addBooking(BookingDTO bookingdto) throws InvalidBookingException;
	public List<BookingDTO> viewBooking(Long bookingId) throws InvalidBookingException;
	public String cancelBooking(long bookingId) throws InvalidBookingException;
	public Optional<BookingDTO> FindById(long bookingId);
	public List<BookingDTO> viewBooking();
	

}
