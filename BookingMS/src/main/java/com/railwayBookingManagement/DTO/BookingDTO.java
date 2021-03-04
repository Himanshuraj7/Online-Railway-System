package com.railwayBookingManagement.DTO;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;


@Entity
@Table(name="booking")
public class BookingDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookingId;
	@Column @NotNull
	private String passengerName;
	@Column @NotNull
	private String PassengerAddress;
	@Column @NotNull
	private Long age;
	
	public long getBookingId() {
		return bookingId;
	}
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getPassengerAddress() {
		return PassengerAddress;
	}
	public void setPassengerAddress(String passengerAddress) {
		PassengerAddress = passengerAddress;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	
	public BookingDTO(){

	}
	
	public BookingDTO(long bookingId, String passengerName, String passengerAddress, Long age) {
		super();
		this.bookingId = bookingId;
		this.passengerName = passengerName;
		PassengerAddress = passengerAddress;
		this.age = age;
	}

}
