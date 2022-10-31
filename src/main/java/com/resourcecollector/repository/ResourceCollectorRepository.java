package com.resourcecollector.repository;

import org.springframework.stereotype.Repository;

import com.resourcecollector.entity.Resource;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ResourceCollectorRepository extends CrudRepository<Resource, Instant>{

	
	/**
	 * Function to fetch system resources from the database. The query takes two input parameters to fetch resources between time T1 and time T2.
	 * @param fromTime This is current time in user specified number of hours
	 * @param toTime This is the current time
	 * @return returns a list of resources fetched
	 */
	@Query("select r from Resource r where r.timestamp >= ?1 and r.timestamp <= ?2")
	public List<Resource> getResource(Instant fromTime, Instant toTime);
}
