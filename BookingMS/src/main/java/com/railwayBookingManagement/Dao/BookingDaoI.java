package com.railwayBookingManagement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.railwayBookingManagement.DTO.BookingDTO;


public interface BookingDaoI extends JpaRepository<BookingDTO, Long> {

}
