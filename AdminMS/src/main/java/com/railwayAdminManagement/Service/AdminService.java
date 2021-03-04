package com.railwayAdminManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.railwayAdminManagement.DTO.AdminDTO;
import com.railwayAdminManagement.Exception.InvalidTrainsException;

@Service
public interface AdminService {
	public String addTrain(AdminDTO trainsdto) throws InvalidTrainsException;
	public AdminDTO viewTrain(Long trainId) throws InvalidTrainsException;
	public String deleteTrain(Long trainId) throws InvalidTrainsException;
	public Optional<AdminDTO> FindById(Long trainId);
	public List<AdminDTO>viewTrain();

	

}
