package com.resourcecollector;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.sun.management.OperatingSystemMXBean;


@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class ResourceCollectorApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceCollectorApplication.class);
	
	private static final int MegaBytes = 10241024;

	public static void main(String[] args) {
		SpringApplication.run(ResourceCollectorApplication.class, args);
	}
}
