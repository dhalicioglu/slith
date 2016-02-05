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
		
		if(this.gameActivity.getTimer().getEndTime() == 0){
			// this part refreshes the game screen
			this.gameActivity.getGameScreen().getGameManager().moveBall(this.gameActivity.getGameScreen().getWidth(), this.gameActivity.getTimer());
			this.gameActivity.getTimerScreen().setText(String.valueOf(duration));
			this.gameActivity.getGameScreen().invalidate();
			this.gameActivity.getHandler().postDelayed(this, 10);
		}
		
	}
	
}
