package com.endieych.gameUnits;

public class Ball {
	private float radius;
	private float centerX;
	private float centerY;
	private float velocity;
	private float angle;
	
	

	// constructor
	public Ball(){
		this.setRadius(15);
		this.setCenterX(400);
		this.setCenterY(10);
		this.setVelocity(5);
		this.setAngle(270);
	}

	// getters for private attributes
	public float getRadius() {
		return radius;
	}
	public float getCenterX() {
		return centerX;
	}
	public float getCenterY() {
		return centerY;
	}
	public float getVelocity() {
		return velocity;
	}
	public float getAngle() {
		return angle;
	}
	
	// setters for private attributes
	public void setRadius(float radius) {
		this.radius = radius;
	}
	public void setCenterX(float centerX) {
		this.centerX = centerX;
	}
	public void setCenterY(float centerY) {
		this.centerY = centerY;
	}
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
}
