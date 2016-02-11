package com.endieych.gameUnits;

public class Stick {
	private float coordinateFront;
	private float coordinateEnd;
	private float length;
	private float coordinateCenter;
	private float coordinateY;
	private float height;
	
	// constructor
	public Stick(){
		this.setCoordinateFront(150);
		this.setCoordinateEnd(320);
		this.setCoordinateY(500);
		this.setLength(170);
	}

	// getters for private attributes
	public float getCoordinateFront() {
		return coordinateFront;
	}
	public float getCoordinateEnd() {
		return coordinateEnd;
	}
	public float getCoordinateCenter() {
		return coordinateCenter;
	}
	public float getHeight() {
		return height;
	}
	public float getCoordinateY() {
		return coordinateY;
	}
	public float getLength() {
		return length;
	}
	
	// setters for private attributes
	public void setCoordinateFront(float coordinateFront) {
		this.coordinateFront = coordinateFront;
	}
	public void setCoordinateEnd(float coordinateEnd) {
		this.coordinateEnd = coordinateEnd;
	}
	public void setCoordinateCenter(float coordinateCenter) {
		this.coordinateCenter = coordinateCenter;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public void setCoordinateY(float coordinateY) {
		this.coordinateY = coordinateY;
	}
	public void setLength(float length) {
		this.length = length;
	}
}
