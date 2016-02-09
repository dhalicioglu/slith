package com.endieych.slith;

import com.endieych.threads.Refresher;
import com.endieych.timeMeasurement.TimeMeasure;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class GameActivity extends ActionBarActivity {
    
	private GameScreen gameScreen;
	private Refresher refresher;
	private Handler handler;
	private TimeMeasure timer;
	
	private TextView timerScreen;
	private TextView statusScreen;
	
	
	// android stuff, on create eg.
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		// initialize gameScreen object
		setGameScreen((GameScreen) findViewById(R.id.gameScreen));
		this.setHandler(new Handler());
		this.setRefresher(new Refresher(this));
		
		setTimerScreen((TextView)findViewById(R.id.Timer));
		setStatusScreen((TextView) findViewById(R.id.Status));
		
		timer = new TimeMeasure();
		
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


}
