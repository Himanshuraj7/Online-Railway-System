package com.railwayAdminManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railwayAdminManagement.DTO.AdminDTO;
import com.railwayAdminManagement.Dao.AdminDao;
import com.railwayAdminManagement.Exception.InvalidTrainsException;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;

	public String addTrain(AdminDTO trainsdto) throws InvalidTrainsException {

		List<AdminDTO> addtrains = adminDao.findAll();
		for (int i = 0; i < addtrains.size(); i++) {
			if (addtrains.get(i).getTrainName().contains(trainsdto.getTrainName())) {
				throw new InvalidTrainsException(
						"Train with " + " " + trainsdto.getTrainName() + " " + "already there");
			}
		}

		adminDao.save(trainsdto);
		return "TrainsAdded";

	}

	@SuppressWarnings("static-access")
	public AdminDTO viewTrain(Long trainId) throws InvalidTrainsException {
		Optional<AdminDTO> viewtrains = adminDao.findById(trainId);
		if (viewtrains.empty() != null) {
			throw new InvalidTrainsException("train details not found");

		}

		return (AdminDTO) adminDao.findAll();
	}


	@SuppressWarnings("static-access")
	public String deleteTrain(Long trainId) throws InvalidTrainsException {
		Optional<AdminDTO> checkTrain = adminDao.findById(trainId);
		if(checkTrain.empty() != null){

			throw new InvalidTrainsException("train details not found");

		}
		else{
			adminDao.deleteById(trainId);
			return "TrainDetailsDeleted";
		}

	}

	@Override
	public Optional<AdminDTO> FindById(Long trainId) {

		return adminDao.findById(trainId);
	}

	@Override
	public List<AdminDTO> viewTrain() {
		return adminDao.findAll();
	}

}
