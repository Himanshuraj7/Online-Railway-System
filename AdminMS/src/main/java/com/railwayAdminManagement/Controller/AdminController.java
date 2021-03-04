package com.railwayAdminManagement.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.railwayAdminManagement.DTO.AdminDTO;
import com.railwayAdminManagement.Exception.InvalidTrainsException;
import com.railwayAdminManagement.Service.AdminService;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;


@RestController
public class AdminController {

	@Autowired
	AdminService trainservice;
	

	@Value("classpath:Admin.graphqls")
	private Resource schemaResource;

	private GraphQL graphQl;


	// parsing the schema file to TypeDefinitionRegistry and passing registry and wiring to GraphqlSchema
	 
	
	@PostConstruct
	public void loadSchema() throws IOException {
		
		File schemaFile = schemaResource.getFile(); 
		TypeDefinitionRegistry registry =new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildwiring();
		GraphQLSchema schema =new SchemaGenerator().makeExecutableSchema(registry, wiring);
		graphQl=GraphQL.newGraphQL(schema).build();
	}


	@SuppressWarnings("rawtypes")
	private RuntimeWiring buildwiring() {
		DataFetcher<List<AdminDTO>> fetcher = data ->{
			return trainservice.viewTrain();
		};
		DataFetcher<Optional> fetcher2 = data ->{
			return  trainservice.FindById(data.getArgument("trainId"));
		};

		DataFetcher<String> fetcher3 = data ->{
			AdminDTO traindto = new AdminDTO();
			traindto.setArrivalDate(data.getArgument("arrivalDate"));
			traindto.setArrivalTime(data.getArgument("arrivalTime"));
			traindto.setDepartureDate(data.getArgument("departureDate"));
			traindto.setDepartureTime(data.getArgument("departureTime"));
			traindto.setDestination(data.getArgument("destination"));
			traindto.setSource(data.getArgument("source"));
			traindto.setTrainName(data.getArgument("mithila"));
			traindto.setDuration(data.getArgument("duration"));
			traindto.setTrainName(data.getArgument("trainName"));

			String result = "Train with TrainName"+" "+traindto.getTrainName()+" "+"already there";

			try {
				result = trainservice.addTrain(traindto);
			} catch (InvalidTrainsException e) {
				e.printStackTrace();
			}
			return result;
		};

		DataFetcher<String> fetcher4 = data ->{
			String result = "TrainId not found";
			try {
				result =  trainservice.deleteTrain(data.getArgument("trainId"));
			} catch (InvalidTrainsException e) {
				e.printStackTrace();
			}
			return result;
		};

		RuntimeWiring runTimeWiring=RuntimeWiring.newRuntimeWiring().type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("findAll",fetcher))
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("findById",fetcher2))
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("addTrain",fetcher3))
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("deleteTrain",fetcher4))
				.build();
		return runTimeWiring;


	}

	@PostMapping("/addTrain")
	public ResponseEntity<Object> addTrain(@RequestBody String query){
		ExecutionResult result = graphQl.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	@PostMapping("/findById")
	public ResponseEntity<Object> findById(@RequestBody String query){
		ExecutionResult result = graphQl.execute(query);
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}

	@PostMapping("/findAll")
	public ResponseEntity<Object> findAll(@RequestBody String query){
		ExecutionResult result = graphQl.execute(query);
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}

	@PostMapping("/deleteTrain")
	public ResponseEntity<Object> deleteTrain(@RequestBody String query){
		ExecutionResult result = graphQl.execute(query);
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}

}
