package com.endieych.slith;

import com.endieych.threads.Refresher;
import com.endieych.timeMeasurement.TimeMeasure;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends ActionBarActivity {
    
	private GameScreen gameScreen;
	private Refresher refresher;
	private Handler handler;
	private TimeMeasure timer;
	
	private TextView timerScreen;
	private TextView statusScreen;
	private TextView scoreScreen;
	private ImageView replayScreen;
	
	
	// android stuff, on create eg.
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		// initialize gameScreen object
		setGameScreen((GameScreen) findViewById(R.id.gameScreen));
		
		// thread and handler is initialized here
		this.setHandler(new Handler());
		this.setRefresher(new Refresher(this));
		
		// initialize 3 text views here
		setTimerScreen((TextView)findViewById(R.id.Timer));
		setStatusScreen((TextView) findViewById(R.id.Status));
		setScoreScreen((TextView) findViewById(R.id.BestScore));
		
		// initialize image view and make it disappear
		this.setReplayScreen((ImageView) findViewById(R.id.replay));
		this.getReplayScreen().setVisibility(View.INVISIBLE);
		
		// initialize TimeMeasure Object here
		timer = new TimeMeasure();
		
		// give the thread to handler and start thread
		this.getHandler().post(this.refresher);
		this.refresher.run();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
	public void replayGame(View view){
		this.finish();
		this.startActivity(this.getIntent());
	}

	
	
	// getters for private attributes
	public GameScreen getGameScreen() {
		return gameScreen;
	}
	public Refresher getRefresher() {
		return refresher;
	}
	public Handler getHandler() {
		return handler;
	}
	public TimeMeasure getTimer() {
		return timer;
	}
	public TextView getTimerScreen() {
		return timerScreen;
	}
	public TextView getStatusScreen() {
		return statusScreen;
	}
	public TextView getScoreScreen() {
		return scoreScreen;
	}
	public ImageView getReplayScreen() {
		return replayScreen;
	}
	
	// setters for private attributes
	public void setGameScreen(GameScreen gameScreen) {
		this.gameScreen = gameScreen;
	}
	public void setRefresher(Refresher refresher) {
		this.refresher = refresher;
	}
	public void setHandler(Handler handler) {
		this.handler = handler;
	}
	public void setTimer(TimeMeasure timer) {
		this.timer = timer;
	}	
	public void setTimerScreen(TextView textView) {
		this.timerScreen = textView;
	}
	public void setStatusScreen(TextView statusScreen) {
		this.statusScreen = statusScreen;
	}
	public void setScoreScreen(TextView scoreScreen) {
		this.scoreScreen = scoreScreen;
	}
	public void setReplayScreen(ImageView replayScreen) {
		this.replayScreen = replayScreen;
	}


}
