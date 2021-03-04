package com.railwayAdminManagement.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.railwayAdminManagement.DTO.AdminDTO;
import com.railwayAdminManagement.Dao.AdminDao;
import com.railwayAdminManagement.Exception.InvalidTrainsException;
import com.railwayAdminManagement.Service.AdminServiceImpl;

import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class AdminControllerTest {

	@InjectMocks
	AdminServiceImpl testserviceimpl;

	@Mock
	AdminDao trainsdaomock;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@SuppressWarnings("deprecation")
	@Test
	void addTrainstest() throws InvalidTrainsException {
		AdminDTO trainsdto = new AdminDTO();
		trainsdto.setTrainId(1L);
		trainsdto.setArrivalTime("08:00");
		trainsdto.setDuration("24hrs");
		trainsdto.setSource("Patna");
		trainsdto.setTrainName("mithila");
		trainsdto.setArrivalDate("2021-02-21");
		trainsdto.setDepartureDate("2021-02-22");
		trainsdto.setDepartureTime("08:00");
		trainsdto.setDestination("Kolkata");


		when( trainsdaomock.save(anyObject()) ).thenReturn(null);
		String Expected =  testserviceimpl.addTrain(trainsdto);
		assertEquals(Expected,"TrainsAdded" );
	}

	@Test
	void viewTrainTest() throws InvalidTrainsException
	{
		AdminDTO trainsdto = new AdminDTO();
		List<AdminDTO> list = new ArrayList<AdminDTO>();

		trainsdto.setArrivalTime("08:00");
		trainsdto.setDuration("24hrs");
		trainsdto.setSource("Patna");
		trainsdto.setTrainName("mithila");
		trainsdto.setArrivalDate("2021-03-10");
		trainsdto.setDepartureDate("2021-03-11");
		trainsdto.setDepartureTime("08:00");
		trainsdto.setDestination("kolkata");
		list.add(trainsdto);


		when(trainsdaomock.findAll()).thenReturn(list);
		assertThrows(InvalidTrainsException.class, () -> {
			testserviceimpl.viewTrain(1L);
		});


	}

	@Test
	void deleteTrainTest() throws InvalidTrainsException
	{
		AdminDTO trainsdto = new AdminDTO();
		List<AdminDTO> list = new ArrayList<AdminDTO>();

		trainsdto.setTrainId(1L);
		trainsdto.setArrivalTime("08:00");
		trainsdto.setDuration("24hrs");
		trainsdto.setSource("Patna");
		trainsdto.setTrainName("mithila");
		trainsdto.setArrivalDate("2021-03-10");
		trainsdto.setDepartureDate("2021-03-11");
		trainsdto.setDepartureTime("08:00");
		trainsdto.setDestination("kolkata");
		list.add(trainsdto);

		doNothing().when(trainsdaomock).deleteById(anyLong());
		assertThrows(InvalidTrainsException.class, () -> {
			testserviceimpl.deleteTrain(1L);
		});
	}
}
