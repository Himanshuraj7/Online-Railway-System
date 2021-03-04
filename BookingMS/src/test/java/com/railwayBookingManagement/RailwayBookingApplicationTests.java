package com.railwayBookingManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.railwayBookingManagement.DTO.BookingDTO;
import com.railwayBookingManagement.Dao.BookingDaoI;
import com.railwayBookingManagement.Exception.InvalidBookingException;
import com.railwayBookingManagement.Service.BookingServiceImpl;

@SpringBootTest
class RailwayBookingApplicationTests {
	@InjectMocks
	BookingServiceImpl bookingservicetest;

	@Mock
	BookingDaoI bookingmock;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void addBookingTest() throws InvalidBookingException {
		BookingDTO bookingdto = new BookingDTO();
		bookingdto.setAge(22L);
		bookingdto.setBookingId(1000);
		bookingdto.setPassengerAddress("Patna");
		bookingdto.setPassengerName("Himanshu");


		when( bookingmock.save(anyObject()) ).thenReturn(null);
		String Expected =  bookingservicetest.addBooking(bookingdto);
		assertEquals(Expected,"BookingAdded" );
	}

	@Test
	void addBookingTest1() throws InvalidBookingException {
		BookingDTO bookingdto = new BookingDTO();
		bookingdto.setAge(22L);
		bookingdto.setBookingId(1000);
		bookingdto.setPassengerAddress("Patna");
		bookingdto.setPassengerName("");


		when( bookingmock.save(anyObject()) ).thenReturn(null);

		assertThrows(InvalidBookingException.class, () -> {
			bookingservicetest.addBooking(bookingdto);
		});

	}

	@Test
	void viewBookingTest() throws InvalidBookingException {
		BookingDTO bookingdto = new BookingDTO();
		List<BookingDTO> list  = new ArrayList<BookingDTO>();
		bookingdto.setAge(22L);
		bookingdto.setBookingId(1000);
		bookingdto.setPassengerAddress("Patna");
		bookingdto.setPassengerName("Himanshu");
		list.add(bookingdto);


		when( bookingmock.findAll() ).thenReturn(list);
		assertThrows( InvalidBookingException.class, () -> {
			bookingservicetest.viewBooking(1L);
		});
	}

	@Test
	void deleteBookingTest() throws InvalidBookingException {
		BookingDTO bookingdto = new BookingDTO();
		List<BookingDTO> list  = new ArrayList<BookingDTO>();
		bookingdto.setAge(22L);
		bookingdto.setBookingId(1000);
		bookingdto.setPassengerAddress("Patna");
		bookingdto.setPassengerName("Himanshu");
		list.add(bookingdto);


		doNothing().when( bookingmock).deleteById(anyLong());
		assertThrows( InvalidBookingException.class, () -> {
			bookingservicetest.cancelBooking(1000);
		});

	}
}
