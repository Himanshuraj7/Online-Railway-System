package com.railwayBookingManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railwayBookingManagement.DTO.BookingDTO;
import com.railwayBookingManagement.Dao.BookingDaoI;
import com.railwayBookingManagement.Exception.InvalidBookingException;


@Service
public class BookingServiceImpl implements BookingServiceI {

	@Autowired
	BookingDaoI bookingdaoi;

	@Override
	public String addBooking(BookingDTO bookingdto) throws InvalidBookingException {

		if(bookingdto.getPassengerName().isEmpty()) {
			throw new InvalidBookingException("BookingName cannot be empty");
		}
		else {
			bookingdaoi.save(bookingdto);
			return "BookingAdded";
		}

	}
	
	@SuppressWarnings("static-access")
	@Override
	public List<BookingDTO> viewBooking(Long bookingId) throws InvalidBookingException {

		Optional<BookingDTO> checkView = bookingdaoi.findById(bookingId);
		if(checkView.empty() != null)
		{
			throw new InvalidBookingException("Booking details with "+ bookingId+" "+"not present ");
		}
		else
		{
			return bookingdaoi.findAll();

		}


	}

	@Override
	public String cancelBooking(long bookingId) throws InvalidBookingException {


		Optional<BookingDTO> checkDelete = bookingdaoi.findById(bookingId);
		if(checkDelete.isPresent())
		{
			bookingdaoi.deleteById(bookingId);
			return "bookingCancelled";
		}
		else
		{
			throw new InvalidBookingException("Booking Id Not Found");
		}


	}
	
	@Override
	public Optional<BookingDTO> FindById(long bookingId) {
		return bookingdaoi.findById(bookingId);
	}
	

	@Override
	public List<BookingDTO> viewBooking() {
		return bookingdaoi.findAll();
	}

}
