package com.example.restfull.RestFullApis.Versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {

	//Versioning through diff url
	@GetMapping(path = "/v1/person")
	public PersonV1 getPersonDetailsV1() {
		return new PersonV1("Rohit Chile");
		
	}
	
	@GetMapping(path = "/v2/person")
	public PersonV2 getPersonDetailsV2() {
		return new PersonV2(new Name("Rohit","Chile"));
		
	}
	
	//Versioning using params
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getPersonDetailsByparameterV1() {
		return new PersonV1("Rohit Chile");
		
	}
	
	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getPersonDetailsByparameterV2() {
		return new PersonV2(new Name("Rohit","Chile"));
		
	}
	
	
	//Versioning using Headers
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
	public PersonV1 getPersonDetailsByHeaderV1() {
		return new PersonV1("Rohit Chile");
		
	}
	
	@GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
	public PersonV2 getPersonDetailsByHeaderV2() {
		return new PersonV2(new Name("Rohit","Chile"));
		
	}
	
	//Versioning using accept
	
	@GetMapping(path = "/person/accept", produces =  "application/vnd.company.app-v1+json")
	public PersonV1 getPersonDetailsByProducesV1() {
		return new PersonV1("Rohit Chile");
		
	}
	
	@GetMapping(path = "/person/accept", produces =  "application/vnd.company.app-v2+json")
	public PersonV2 getPersonDetailsByProducesV2() {
		return new PersonV2(new Name("Rohit","Chile"));
		
	}
}
