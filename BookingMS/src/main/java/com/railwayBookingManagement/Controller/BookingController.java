package com.railwayBookingManagement.Controller;

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

import com.railwayBookingManagement.DTO.BookingDTO;
import com.railwayBookingManagement.Exception.InvalidBookingException;
import com.railwayBookingManagement.Service.BookingServiceI;

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
public class BookingController {

	@Autowired
	BookingServiceI bookingservicei;

	@Value("classpath:Booking.graphqls")
	private Resource schemaResource;
	
	private GraphQL graphQl;
	
	@PostConstruct
	public void loadSchema() throws IOException {
		File schemaFile = schemaResource.getFile(); 
		TypeDefinitionRegistry registry =new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildwiring();
		GraphQLSchema schema =new SchemaGenerator().makeExecutableSchema(registry, wiring);
		graphQl=GraphQL.newGraphQL(schema).build();
	}
	
	
	private RuntimeWiring buildwiring() {
		DataFetcher<List<BookingDTO>> fetcher = data ->{
			return bookingservicei.viewBooking();
		};
		@SuppressWarnings("rawtypes")
		DataFetcher<Optional> fetcher2 = data ->{
				return  bookingservicei.FindById(data.getArgument("bookingId"));
				
		};
		
		DataFetcher<String> fetcher3 = data ->{
			BookingDTO bookingdto = new BookingDTO();
			
			bookingdto.setPassengerName( data.getArgument("passengerName"));
			bookingdto.setPassengerAddress(data.getArgument("passengerAddress"));
			bookingdto.setAge(data.getArgument("age"));
			String result = "xyz";
			
			try {
				 result =  bookingservicei.addBooking(bookingdto);
			} catch (InvalidBookingException e) {
				e.printStackTrace();
			}
			return result;
			
		};
		DataFetcher<String> fetcher4 = data ->{
			String result="BookingId Not Found";
				 try {
					 result =  bookingservicei.cancelBooking(data.getArgument("bookingId"));
				} catch (InvalidBookingException e) {
					e.printStackTrace();
				}
			return result;
			
		};
		
		RuntimeWiring runTimeWiring=RuntimeWiring.newRuntimeWiring().type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("findAll",fetcher))
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("findById",fetcher2))
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("addBooking",fetcher3))
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("cancelBooking",fetcher4))
				.build();
		return runTimeWiring;
	}

	
	@PostMapping("/addBooking")
	public ResponseEntity<Object> addBooking(@RequestBody String query){
		ExecutionResult result = graphQl.execute(query);
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}

	
	@PostMapping("/findAll")
	public ResponseEntity<Object> findAll(@RequestBody String query){
		ExecutionResult result = graphQl.execute(query);
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}
	
	@PostMapping("/findById")
	public ResponseEntity<Object> findById(@RequestBody String query){
		ExecutionResult result = graphQl.execute(query);
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}
	
	@PostMapping("/cancelBooking")
	public ResponseEntity<Object> cancelBooking(@RequestBody String query){
		ExecutionResult result = graphQl.execute(query);
		return new ResponseEntity<Object>(result,HttpStatus.OK);
	}

}
