package com.resourcecollector.entity;

import java.util.List;

public class ResourceSummary {

	/*
	 * This represent the system resource at the current time
	 */
	private Resource currentState;
	
	/*
	 * This represent the historical system resources
	 */
	private List<Resource> historicalState;

	public Resource getCurrentState() {
		return currentState;
	}

	public List<Resource> getHistoricalState() {
		return historicalState;
	}

	public void setCurrentState(Resource currentState) {
		this.currentState = currentState;
	}

	public void setHistoricalState(List<Resource> historicalState) {
		this.historicalState = historicalState;
	}
}
