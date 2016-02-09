package com.endieych.GameManager;

import com.endieych.gameUnits.Ball;
import com.endieych.gameUnits.Obstacle;
import com.endieych.gameUnits.Stick;
import com.endieych.timeMeasurement.TimeMeasure;

public class GameManager {
	private Stick stick;
	private Ball ball;
	private Obstacle obstacle;
	private boolean isGameStarted = false;
	
	// constructor
	public GameManager(){
		this.stick = new Stick();
		this.ball = new Ball();
		this.obstacle = new Obstacle();
	}
	
	
	// GENERAL METHODS
	
	// check if the ball touches the stick
	public boolean isIntersect(Stick stick, Ball ball){
		if((this.getStick().getCoordinateY() - this.getBall().getCenterY()) <= this.getBall().getRadius()){
			if(this.getBall().getCenterX() >= this.stick.getCoordinateFront() && this.getBall().getCenterX() <= this.stick.getCoordinateEnd()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	
	// check if the ball touches the obstacle
	public boolean isIntersectBallObstacle(Ball ball, Obstacle obstacle){
		float xMinus;
		float yMinus;
		double distance;
		
		xMinus = Math.abs(ball.getCenterX() - obstacle.getCoordinateX());
		yMinus = Math.abs(ball.getCenterY() - obstacle.getCoordinateY());
		
		distance = Math.sqrt((xMinus*xMinus) + (yMinus*yMinus));
		
		if(distance <= ball.getRadius() + obstacle.getRadius()){
			return true;
		}else{
			return false;
		}
		
	}
	
	// moves the ball according to its speed and angle
	public void moveBall(float screenWidth, TimeMeasure timer){
		double angleRadian = Math.toRadians(this.ball.getAngle());
		
		if(this.isIntersect(this.stick, this.ball)){
			if(!isGameStarted){
				timer.startTimer();
				this.collideWithStick();
				this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
				this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
				this.setGameStarted(true);
			}else{
				this.collideWithStick();
				this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
				this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
			}
		}else if(this.getBall().getCenterX() <= this.getBall().getRadius()){
			this.collideWithLeftBorder();
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
			if(this.isIntersectBallObstacle(this.ball, this.obstacle)){
				this.ball.setVelocity((float) (this.ball.getVelocity() * 1.03));
			}
		}else if(this.getBall().getCenterX() >= screenWidth - this.getBall().getRadius()){
			this.collideWithRightBorder();
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
			if(this.isIntersectBallObstacle(this.ball, this.obstacle)){
				this.ball.setVelocity((float) (this.ball.getVelocity() * 1.03));
			}
		}else if(this.getBall().getCenterY() <= this.getBall().getRadius()){
			this.collideWithTopBorder();
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
		}else{
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
			if(this.isIntersectBallObstacle(this.ball, this.obstacle)){
				this.ball.setVelocity((float) (this.ball.getVelocity() * 1.03));
			}
		}
		
		if(this.getBall().getCenterY() - (this.getBall().getRadius() * 2) > this.getStick().getCoordinateY()){
			timer.endTimer();
		}
	}
	
	
	public void moveObstacle(int width, int height) {
		double angleRadian = Math.toRadians(this.getObstacle().getAngle());
		
		if(this.getObstacle().getCoordinateX() <= this.getObstacle().getRadius()){
			this.getObstacle().setAngle(0);
			this.getObstacle().setCoordinateX(this.getObstacle().getCoordinateX() + (float) (Math.cos(angleRadian) * this.getObstacle().getVelocity()));
			this.getObstacle().setCoordinateY((height/100)*22);
		}else if(this.getObstacle().getCoordinateX() >= width - this.getObstacle().getRadius()){
			this.getObstacle().setAngle(180);
			this.getObstacle().setCoordinateX(this.getObstacle().getCoordinateX() + (float) (Math.cos(angleRadian) * this.getObstacle().getVelocity()));
			this.getObstacle().setCoordinateY((height/100)*22);
		}else{
			this.getObstacle().setCoordinateX(this.getObstacle().getCoordinateX() + (float) (Math.cos(angleRadian) * this.getObstacle().getVelocity()));
			this.getObstacle().setCoordinateY((height/100)*22);	
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
	public boolean isGameStarted() {
		return isGameStarted;
	}
	public Obstacle getObstacle() {
		return obstacle;
	}
	
	// setters for private attributes
	public void setStick(Stick stick) {
		this.stick = stick;
	}
	public void setBall(Ball ball) {
		this.ball = ball;
	}
	public void setGameStarted(boolean isGameStarted) {
		this.isGameStarted = isGameStarted;
	}
	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}


	
}
