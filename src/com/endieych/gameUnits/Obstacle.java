package com.endieych.gameUnits;

public class Obstacle {
	
	private float coordinateX;
	private float coordinateY;
	private float radius;
	private float velocity;
	private float Angle;
	
	public Obstacle(){
		this.setCoordinateX(200);
		this.setCoordinateY(200);
		this.setRadius(25);
		this.setVelocity(2);
		this.setAngle(180);
	}

	// getters for private attributes
	public float getCoordinateX() {
		return coordinateX;
	}
	public float getCoordinateY() {
		return coordinateY;
	}
	public float getRadius() {
		return radius;
	}
	public float getVelocity() {
		return velocity;
	}
	public float getAngle() {
		return Angle;
	}
	
	// setters for private attributes
	public void setCoordinateX(float coordinateX) {
		this.coordinateX = coordinateX;
	}
	public void setCoordinateY(float coordinateY) {
		this.coordinateY = coordinateY;
	}
	public void setRadius(float radius) {
		this.radius = radius;
	}
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	public void setAngle(float angle) {
		Angle = angle;
	}
	
	
}
