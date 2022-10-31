package com.resourcecollector.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.resourcecollector.entity.ResourceSummary;
import com.resourcecollector.service.ResourceService;

@RestController
public class ResourceCollectorRestController {

	@Autowired
	private ResourceService service;
	
	/*
	 * HTTP GET API to fetch system resources
	 */
	@GetMapping(path = "/api/resources/{hours}")
	private ResourceSummary getResources(@PathVariable int hours){
		return service.getResources(hours);
	}
}
