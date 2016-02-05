package com.endieych.threads;

import com.endieych.slith.GameActivity;

public class Refresher implements Runnable{
	private GameActivity gameActivity;
	
	public Refresher(GameActivity gameAcvtivity){
		this.gameActivity = gameAcvtivity;
	}
	
	@Override
	public void run() {
		
		// this part refreshes the game screen
		this.gameActivity.getGameScreen().getGameManager().moveBall(gameActivity.getGameScreen().getWidth());
		this.gameActivity.getGameScreen().invalidate();
		this.gameActivity.getHandler().postDelayed(this, 10);
		
	}
	
}
