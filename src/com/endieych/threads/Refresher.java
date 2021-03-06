package com.endieych.threads;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.view.View;

import com.endieych.slith.GameActivity;

public class Refresher implements Runnable{
	private GameActivity gameActivity;
	
	public Refresher(GameActivity gameAcvtivity){
		this.gameActivity = gameAcvtivity;
	}
	
	@SuppressLint("NewApi")
	@Override
	public void run() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this.gameActivity);
		
		double currentTime = this.gameActivity.getTimer().getCurrentTime();
		double duration = (currentTime - this.gameActivity.getTimer().getStartTime()) / 1000;
		
		
		// this part refreshes the game screen
		// if the game has not ended yet
		if(this.gameActivity.getTimer().getEndTime() == 0){
			// move the ball
			this.gameActivity.getGameScreen().getGameManager().moveBall(this.gameActivity.getGameScreen().getWidth(), this.gameActivity.getTimer());
			
			// move the obstacle
			this.gameActivity.getGameScreen().getGameManager().moveObstacle(this.gameActivity.getGameScreen().getWidth(), this.gameActivity.getGameScreen().getHeight());
			
			// refresh timer on the screen
			if(this.gameActivity.getGameScreen().getGameManager().isGameStarted()){
				this.gameActivity.getTimerScreen().setText(String.valueOf(duration));
			}
			
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
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 36 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 42){
				this.gameActivity.getStatusScreen().setText("Alien detected!");
			}else if(this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() > 42 && this.gameActivity.getGameScreen().getGameManager().getBall().getVelocity() <= 50){
				this.gameActivity.getStatusScreen().setText("Chuck Norris");
			}
			
			
			// refresh best score on screen
			this.gameActivity.getScoreScreen().setText("Best Score:\n" + sp.getFloat("score", 0));
	
			// refresh screen
			this.gameActivity.getGameScreen().invalidate();
				
			// delay the refresh time
			this.gameActivity.getHandler().postDelayed(this, 10);
		}else{
			// if the game is over
			// refresh the timer one last time on the screen
			if(this.gameActivity.getGameScreen().getGameManager().isGameStarted()){
				this.gameActivity.getTimerScreen().setText(String.valueOf(duration));
			}else{
				// if the game is over before even it is started
				this.gameActivity.getTimerScreen().setText(String.valueOf(0.000));
				duration = 0;
			}
			
			
			// if the score is better than the best score, store it
			if(duration > sp.getFloat("score", 0)){
				Editor editor = sp.edit();
				editor.putFloat("score", (float) duration);
				editor.commit();
			}
			
			
			// make the replay screen visible
			this.gameActivity.getReplayScreen().setVisibility(View.VISIBLE);
			
		}
		
	}
	
}
