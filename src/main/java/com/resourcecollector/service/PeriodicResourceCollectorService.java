package com.resourcecollector.service;

import java.lang.management.ManagementFactory;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.resourcecollector.entity.Resource;
import com.resourcecollector.repository.ResourceCollectorRepository;
import com.sun.management.OperatingSystemMXBean;

@Service
public class PeriodicResourceCollectorService {

	private static final Logger logger = LoggerFactory.getLogger(PeriodicResourceCollectorService.class);
	
	@Autowired
	private ResourceCollectorRepository repository;
	
	private OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory
            .getOperatingSystemMXBean();
	
	/**
	 * This method will be triggered every 30 seconds. It will collect system resources and calls 
	 * the repository layer to insert data into the database.
	 */
	@Scheduled(fixedRate = 30000)
	protected void collectResources() throws Exception{
		
		double cpuLoad = collectCPUUtilization();
		double memoryUsed = collectMemoryUtilization();
		
		Resource resource = new Resource();
		resource.setCpuUtilization(cpuLoad);
		resource.setMemoryUtilization(memoryUsed);
		
		Instant now = Instant.now();
		resource.setTimestamp(now);
		
		logger.info("collectResources() - collecting system resources at {} ", now);
		repository.save(resource);
	}
	
	/*
	 * Function to collect CPU Utilization percentage
	 */
	public double collectCPUUtilization() {
		double cpuLoad = bean.getProcessCpuLoad();
		return cpuLoad * 100;
	}
	
	/*
	 * Function to collect memory utilization percentage.
	 * It subtract the free memory from the total available memory and then divide by total memory to get the percentage of used memory
	 */
	public long collectMemoryUtilization() {
		long total = bean.getTotalPhysicalMemorySize();
		long free = bean.getFreePhysicalMemorySize();
		return (total - free) * 100 / total;
	}
}
