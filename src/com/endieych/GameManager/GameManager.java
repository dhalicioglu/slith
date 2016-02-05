package com.endieych.GameManager;

import com.endieych.gameUnits.Ball;
import com.endieych.gameUnits.Stick;

public class GameManager {
	private Stick stick;
	private Ball ball;
	
	// constructor
	public GameManager(){
		this.stick = new Stick();
		this.ball = new Ball();
	}
	
	
	// GENERAL METHODS
	
	// check if the ball touches the stick
	public boolean isIntersect(Stick stick, Ball ball){
		if((this.getStick().getCoordinateY() - this.getBall().getCenterY()) <= this.getBall().getRadius()){
			if(this.getBall().getCenterX() >= this.stick.getCoordinateFront() && this.getBall().getCenterX() <= this.stick.getCoordinateEnd()){
				return true;
			}else{
				// GAME OVER
				return false;
			}
		}else{
			return false;
		}
	}
	
	// moves the ball according to its speed and angle
	public void moveBall(float screenWidth){
		double angleRadian = Math.toRadians(this.ball.getAngle());
		
		if(this.isIntersect(this.stick, this.ball)){
			this.collideWithStick();
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
		}else if(this.getBall().getCenterX() <= this.getBall().getRadius()){
			this.collideWithLeftBorder();
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
		}else if(this.getBall().getCenterX() >= screenWidth - this.getBall().getRadius()){
			this.collideWithRightBorder();
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
		}else if(this.getBall().getCenterY() <= this.getBall().getRadius()){
			this.collideWithTopBorder();
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
		}else{
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
		}
	}
	
	// what happens when the ball touches the stick
	public void collideWithStick(){
		float angleTuner = 1;
		angleTuner = (-1 * (this.getBall().getCenterX() - this.getStick().getCoordinateCenter()) / (this.getStick().getLength()/2));
		
		if(this.getBall().getAngle() >= 180 && this.getBall().getAngle() <= 270){
			this.getBall().setAngle((float) (180 - (this.getBall().getAngle() - 180)) + angleTuner * (this.getBall().getAngle() - 180));
		}else if(this.getBall().getAngle() > 270 && this.getBall().getAngle() <= 360){
			this.getBall().setAngle((360 - this.getBall().getAngle()) + angleTuner * (360 - this.getBall().getAngle()));
		}
	}
	
	// what happens when the ball touches left border
	public void collideWithLeftBorder(){
		if(this.getBall().getAngle() >= 90 && this.getBall().getAngle() <= 180){
			this.getBall().setAngle(180 - this.getBall().getAngle());
		}else if(this.getBall().getAngle() > 180 && this.getBall().getAngle() <= 270){
			this.getBall().setAngle(360 - (this.getBall().getAngle() - 180));
		}
	}
	
	// what happens when the ball touches right border
	public void collideWithRightBorder(){
		if(this.getBall().getAngle() >= 0 && this.getBall().getAngle() <= 90){
			this.getBall().setAngle(180 - this.getBall().getAngle());
		}else if(this.getBall().getAngle() > 270 && this.getBall().getAngle() <= 360){
			this.getBall().setAngle(360 - (this.getBall().getAngle() - 180));
		}
	}
	
	// what happens when the ball touches top border
	public void collideWithTopBorder(){
		if(this.getBall().getAngle() >= 0 && this.getBall().getAngle() <= 90){
			this.getBall().setAngle(360 - this.getBall().getAngle());
		}else if(this.getBall().getAngle() > 90 && this.getBall().getAngle() <= 180){
			this.getBall().setAngle(180 + (180 - this.getBall().getAngle()));
		}
	}
	

	
	
	// getters for private attributes
	public Stick getStick() {
		return stick;
	}
	public Ball getBall() {
		return ball;
	}
		
	// setters for private attributes
	public void setStick(Stick stick) {
		this.stick = stick;
	}
	public void setBall(Ball ball) {
		this.ball = ball;
	}
}
