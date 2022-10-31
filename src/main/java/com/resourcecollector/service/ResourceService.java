package com.resourcecollector.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resourcecollector.entity.Resource;
import com.resourcecollector.entity.ResourceSummary;
import com.resourcecollector.repository.ResourceCollectorRepository;

@Service
public class ResourceService {

	private static final Logger logger = LoggerFactory.getLogger(ResourceService.class);
	
	@Autowired
	private ResourceCollectorRepository repository;
	
	@Autowired
	private PeriodicResourceCollectorService periodicResourceCollectorService;
	
	/**
	 * Function to get system resources for a input number of hours.
	 * @param hours user specified number of hours for which historical data will be returned
	 * @return return a @ResourceSummary object
	 */
	public ResourceSummary getResources(int hours) {
		logger.info("getResources() - resources requested for {} hours", hours);
		
		ResourceSummary summary = new ResourceSummary();
		
		double cpuLoad = periodicResourceCollectorService.collectCPUUtilization();
		double memoryUsed = periodicResourceCollectorService.collectMemoryUtilization();
		
		summary.setCurrentState(new Resource(cpuLoad, memoryUsed));
		
		Instant currentTime = Instant.now();
		
		Instant oldTime = currentTime.minus(hours, ChronoUnit.HOURS);
		
		summary.setHistoricalState(repository.getResource(oldTime, currentTime));
		
		return summary;
	}
	
	
}
