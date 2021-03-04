package com.railwayAdminManagement.DTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="admin")
public class AdminDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long trainId;
	@Column @NotNull
	private String trainName;
	@Column @NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private String arrivalDate;
	@Column @NotNull
	@JsonFormat(pattern="yyyy-MM-dd")
	private String departureDate;
	@Column @NotNull
	private String source;
	@Column @NotNull
	private String destination;
	@Column @NotNull
	private String arrivalTime;
	@Column @NotNull
	private String departureTime;
	@Column @NotNull
	private String duration;
	
	public long getTrainId() {
		return trainId;
	}
	public void setTrainId(long trainId) {
		this.trainId = trainId;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public String getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(String departureDate) {
		this.departureDate = departureDate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public  AdminDTO(){
	}
	
	public AdminDTO(long trainId, String trainName, String arrivalDate, String departureDate, String source,
			String destination, String arrivalTime, String departureTime, String duration) {
		super();
		this.trainId = trainId;
		this.trainName = trainName;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.source = source;
		this.destination = destination;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.duration = duration;
	}
	
}
