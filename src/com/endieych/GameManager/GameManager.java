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
		// if the ball reaches the Y coordinate of the stick
		if((this.getStick().getCoordinateY() - this.getBall().getCenterY()) <= this.getBall().getRadius()){
			// if the ball is between X coordinates of the stick (front-end)
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
		
		// if the radius of the ball plus radius of obstacle equals to distance between them
		// it means they touch eachother
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
			// if the ball touches the stick
			if(!isGameStarted){
				// if the game has not started yet
				timer.startTimer();
				this.getBall().setVelocity(5);
				this.collideWithStick();
				this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
				this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
				this.setGameStarted(true);
			}else{
				// if the game has started already
				this.collideWithStick();
				this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
				this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
			}
		}else if(this.getBall().getCenterX() <= this.getBall().getRadius()){
			// if the ball touches the left border of the screen
			this.collideWithLeftBorder();
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
			// if the ball touches obstacle, make it faster
			if(this.isIntersectBallObstacle(this.ball, this.obstacle)){
				this.ball.setVelocity((float) (this.ball.getVelocity() * 1.03));
			}
		}else if(this.getBall().getCenterX() >= screenWidth - this.getBall().getRadius()){
			// if the ball touches the right border of the screen
			this.collideWithRightBorder();
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
			// if the ball touches obstacle, make it faster
			if(this.isIntersectBallObstacle(this.ball, this.obstacle)){
				this.ball.setVelocity((float) (this.ball.getVelocity() * 1.03));
			}
		}else if(this.getBall().getCenterY() <= this.getBall().getRadius()){
			// if the ball touches the top border of the screen
			this.collideWithTopBorder();
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
		}else{
			// if the ball touches nothing, just doing its normal movement
			this.getBall().setCenterX(this.getBall().getCenterX() + (float) (Math.cos(angleRadian) * this.getBall().getVelocity()));
			this.getBall().setCenterY(this.getBall().getCenterY() + (float) (Math.sin(angleRadian) * this.getBall().getVelocity() * -1));
			// if the ball touches obstacle, make it faster
			if(this.isIntersectBallObstacle(this.ball, this.obstacle)){
				this.ball.setVelocity((float) (this.ball.getVelocity() * 1.03));
			}
		}
		
		// if the ball goes below the coordinate Y of the stick
		// GAME OVER
		if(this.getBall().getCenterY() - (this.getBall().getRadius() * 2) > this.getStick().getCoordinateY()){
			timer.endTimer();
		}
	}
	
	
	public void moveObstacle(int width, int height) {
		double angleRadian = Math.toRadians(this.getObstacle().getAngle());
		
		if(this.getObstacle().getCoordinateX() <= this.getObstacle().getRadius()){
			// if the obstacle touches left border of the screen
			this.getObstacle().setAngle(0);
			this.getObstacle().setCoordinateX(this.getObstacle().getCoordinateX() + (float) (Math.cos(angleRadian) * this.getObstacle().getVelocity()));
			this.getObstacle().setCoordinateY((height/100)*22);
		}else if(this.getObstacle().getCoordinateX() >= width - this.getObstacle().getRadius()){
			// if the obstacle touches right border of the screen
			this.getObstacle().setAngle(180);
			this.getObstacle().setCoordinateX(this.getObstacle().getCoordinateX() + (float) (Math.cos(angleRadian) * this.getObstacle().getVelocity()));
			this.getObstacle().setCoordinateY((height/100)*22);
		}else{
			// if the obstacle touches nothing, just doing its normal movement
			this.getObstacle().setCoordinateX(this.getObstacle().getCoordinateX() + (float) (Math.cos(angleRadian) * this.getObstacle().getVelocity()));
			this.getObstacle().setCoordinateY((height/100)*22);	
		}
	
	}
	
	
	// what happens when the ball touches the stick
	public void collideWithStick(){
		// angleTuner is used for changing the angle of the ball
		// if the ball touches the most left part of stick
		// it means ball will tend to bend to the left more
		// and same for the right side
		float angleTuner = 1;
		angleTuner = (-1 * (this.getBall().getCenterX() - this.getStick().getCoordinateCenter()) / (this.getStick().getLength()/2));
		System.out.println(angleTuner);
		// if the angleTuner is too low
		// it means the ball will tend to bend to side too much
		// to prevent this, i am putting a limit of bending, in another word a limit of angleTuner
		if(angleTuner > 0.91 && angleTuner < 1){
			angleTuner = (float) 0.91;
		}else if (angleTuner < -0.91 && angleTuner > -1){
			angleTuner = (float) -0.91;
		}
		
		
		if(this.getBall().getAngle() >= 180 && this.getBall().getAngle() <= 270){
			// if the ball coming from right side
			this.getBall().setAngle((float) (180 - (this.getBall().getAngle() - 180)) + angleTuner * (this.getBall().getAngle() - 180));
		}else if(this.getBall().getAngle() > 270 && this.getBall().getAngle() <= 360){
			// if the ball coming from left side
			this.getBall().setAngle((360 - this.getBall().getAngle()) + angleTuner * (360 - this.getBall().getAngle()));
		}
	}
	
	
	// what happens when the ball touches left border
	public void collideWithLeftBorder(){ 
		if(this.getBall().getAngle() >= 90 && this.getBall().getAngle() <= 180){
			// if the ball coming from down side
			this.getBall().setAngle(180 - this.getBall().getAngle());
		}else if(this.getBall().getAngle() > 180 && this.getBall().getAngle() <= 270){
			// if the ball coming from upper side
			this.getBall().setAngle(360 - (this.getBall().getAngle() - 180));
		}
	}
	
	
	// what happens when the ball touches right border
	public void collideWithRightBorder(){
		if(this.getBall().getAngle() >= 0 && this.getBall().getAngle() <= 90){
			// if the ball coming from down side
			this.getBall().setAngle(180 - this.getBall().getAngle());
		}else if(this.getBall().getAngle() > 270 && this.getBall().getAngle() <= 360){
			// if the ball coming from upper side
			this.getBall().setAngle(360 - (this.getBall().getAngle() - 180));
		}
	}
	
	
	// what happens when the ball touches top border
	public void collideWithTopBorder(){
		if(this.getBall().getAngle() >= 0 && this.getBall().getAngle() <= 90){
			// if the ball coming from left side
			this.getBall().setAngle(360 - this.getBall().getAngle());
		}else if(this.getBall().getAngle() > 90 && this.getBall().getAngle() <= 180){
			// if the ball coming from right side
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
