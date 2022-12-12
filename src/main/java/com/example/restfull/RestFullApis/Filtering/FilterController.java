package com.example.restfull.RestFullApis.Filtering;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilterController {

	@GetMapping(path = "/filter")
	public MappingJacksonValue getSomeData() {
		
SomeData someData = new SomeData("value1","value2","value3");
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someData);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeamFilter", filter );
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
		
	}
	
	
	@GetMapping(path = "/filter-dynamic")
	public MappingJacksonValue getSomeDataFordynamicFilter() {
		
		SomeData someData = new SomeData("value1","value2","value3");
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someData);
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeamFilter", filter );
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
		
	}
}
