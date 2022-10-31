package com.resourcecollector.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "resource")
@Data
public class Resource implements Serializable{

	private static final long serialVersionUID = -7704078987496744553L;

	@Column(name = "cpuUtilization")
	private double cpuUtilization;
	
	@Column(name = "memoryUtilization")
	private double memoryUtilization;
	
	@Id
	private Instant timestamp;
	
	public Resource() {
	}
	
	public Resource(double cpuUtilization, double memoryUtilization) {
		this.cpuUtilization = cpuUtilization;
		this.memoryUtilization = memoryUtilization;
	}
	
	public double getCpuUtilization() {
		return cpuUtilization;
	}
	public void setCpuUtilization(double cpuUtilization) {
		this.cpuUtilization = cpuUtilization;
	}
	public double getMemoryUtilization() {
		return memoryUtilization;
	}
	public void setMemoryUtilization(double memoryUtilization) {
		this.memoryUtilization = memoryUtilization;
	}
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
}
