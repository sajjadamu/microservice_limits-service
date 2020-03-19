package com.in28minutes.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.limitsservice.bean.LimitConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class LimitConfigurationController {

	@Autowired
	private Configuration configuration;

	@GetMapping("limits")
	public LimitConfiguration retriveLimitsFromConfigurations() {
		return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
	}

	@GetMapping("fault-tolerance-example")
	@HystrixCommand(fallbackMethod="fallbackRetrievConfiguration")
	public LimitConfiguration retrievConfiguration(){
		throw new RuntimeException("not Available");
	}
	
	public LimitConfiguration fallbackRetrievConfiguration(){
		return new LimitConfiguration(999, 9);
	}
}
