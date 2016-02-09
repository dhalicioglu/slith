package com.endieych.timeMeasurement;

public class TimeMeasure {
	private double startTime;
	private double endTime;
	
	
	public TimeMeasure(){
		this.setEndTime(0);
	}
	
	// methods
	
	// start timer
	public void startTimer(){
		this.setStartTime(System.currentTimeMillis());
	}
	// end timer
	public void endTimer(){
		this.setEndTime(System.currentTimeMillis());
	}
	// get current time
	public double getCurrentTime(){
		return System.currentTimeMillis();
	}
	
	
	
	// getters for private attributes
	public double getStartTime() {
		return startTime;
	}
	public double getEndTime() {
		return endTime;
	}
	
	
	// setters for private attributes
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
}
