package com.endieych.threads;

import com.endieych.slith.GameActivity;

public class Refresher implements Runnable{
	private GameActivity gameActivity;
	
	public Refresher(GameActivity gameAcvtivity){
		this.gameActivity = gameAcvtivity;
	}
	
	@Override
	public void run() {
		
		double currentTime = System.currentTimeMillis();
		double duration = (currentTime - this.gameActivity.getTimer().getStartTime()) / 1000;
		
		// this part refreshes the game screen
		if(this.gameActivity.getTimer().getEndTime() == 0){
			// move the ball
			this.gameActivity.getGameScreen().getGameManager().moveBall(this.gameActivity.getGameScreen().getWidth(), this.gameActivity.getTimer());
			
			// move the obstacle
			this.gameActivity.getGameScreen().getGameManager().moveObstacle(this.gameActivity.getGameScreen().getWidth(), this.gameActivity.getGameScreen().getHeight());
			
			// refresh timer on the screen
			this.gameActivity.getTimerScreen().setText(String.valueOf(duration));
			
			// refresh status on the screen
			if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 6){
				this.gameActivity.getStatusScreen().setText("Easy?");
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 6 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 8){
				this.gameActivity.getStatusScreen().setText("Still Easy?");
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 8 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 10){
				this.gameActivity.getStatusScreen().setText("Meehhh");
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 10 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 12){
				this.gameActivity.getStatusScreen().setText("What the...");
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 12 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 14){
				this.gameActivity.getStatusScreen().setText("Hold my beer");
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 14 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 17){
				this.gameActivity.getStatusScreen().setText("Just a second mom");
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 17 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 20){
				this.gameActivity.getStatusScreen().setText("Holy sh..");
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 20 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 23){
				this.gameActivity.getStatusScreen().setText("Mother....");
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 23 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 27){
				this.gameActivity.getStatusScreen().setText("Super Saiyan");
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 27 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 31){
				this.gameActivity.getStatusScreen().setText("Dragon");
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 31 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 36){
				this.gameActivity.getStatusScreen().setText("GOD");
			}
			
			
			// refresh screen
			this.gameActivity.getGameScreen().invalidate();
			
			// delay the refresh time
			this.gameActivity.getHandler().postDelayed(this, 10);
		}
		
	}
	
}
