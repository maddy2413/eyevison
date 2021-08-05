package com.in28minutes.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {
	
	private Logger loger=LoggerFactory.getLogger(CircuitBreakerController.class);
	
	private ResponseEntity<String> forEntity;

	@GetMapping("/sample-api")
//	@Retry(name = "sample-api",fallbackMethod = "hardCodedResponse")
	@CircuitBreaker(name="defult",fallbackMethod ="hardCodedResponse")	
	public String sampleApi() {
		
		loger.info("sample api called ");
		forEntity = new RestTemplate().getForEntity("http://localhost:8080/dummy_url", String.class);
		//return "Sample Biju Circuit";
		
		return forEntity.getBody();
	}
	
	
	@GetMapping("/sample-apis")
	@RateLimiter(name="default")
	//@Bulkhead(name="sample-apis")
	public String sampleApis() {		
		loger.info("sample api called ");		
		
		return "Retry test";
	}
	
	/*
	"Error will throw if not added exception  as follows 
	Caused by: java.lang.NoSuchMethodException: class java.lang.String class com.in28minutes.microservices.currencyexchangeservice.CircuitBreakerController.hardCodedResponse(,class java.lang.Throwable)""
	*/
	public String hardCodedResponse(Exception exception) {
		
		return "fall-back-response";
		
		
	}

}
