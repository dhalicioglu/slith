package com.endieych.slith;

import com.endieych.GameManager.GameManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class GameScreen extends View{
	
	private GameManager gameManager = new GameManager();
	private Paint paint = new Paint();
	private Bitmap imageStick = BitmapFactory.decodeResource(getResources(), R.drawable.stick);
	private Bitmap imageBall =  BitmapFactory.decodeResource(getResources(), R.drawable.ball);

	public GameScreen(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public GameScreen(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	public GameScreen(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	
	


	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// arrange the size of the stick and ball
		this.setImageStick(Bitmap.createScaledBitmap(this.getImageStick(), (int) (gameManager.getStick().getCoordinateFront() - gameManager.getStick().getCoordinateEnd()), canvas.getHeight()/30, true));
		this.setImageBall(Bitmap.createScaledBitmap(this.getImageBall(), (int) gameManager.getBall().getRadius()*2, (int) (gameManager.getBall().getRadius()*2), true));
		// set the Y coordinate of stick and ball
		gameManager.getStick().setCoordinateY(canvas.getHeight() - canvas.getHeight()/10);
		// set the height of the stick
		gameManager.getStick().setHeight(canvas.getHeight() - canvas.getHeight()/20);
		// draw stick and ball
		canvas.drawBitmap(this.getImageStick(), gameManager.getStick().getCoordinateFront(), gameManager.getStick().getCoordinateY(), paint);
		canvas.drawBitmap(this.getImageBall(), gameManager.getBall().getCenterX() - gameManager.getBall().getRadius(), gameManager.getBall().getCenterY() - gameManager.getBall().getRadius(), paint);
	}
	
	
	@SuppressLint("ClickableViewAccessibility")
	public boolean onTouchEvent(MotionEvent e){
		
		switch(e.getAction()){	
			case MotionEvent.ACTION_DOWN:
				System.out.println("DOWN");
				break;
			case MotionEvent.ACTION_UP:
				System.out.println("UP");
				break;
			case MotionEvent.ACTION_MOVE:
				// if the stick tries to pass the left border of the screen
				if(this.gameManager.getStick().getCoordinateFront() <= 0){
					// if the stick had passed the left border of the screen before and now it's coming back to legal borders
					if(e.getRawX() - this.gameManager.getStick().getLength()/2 > 0){
						this.gameManager.getStick().setCoordinateCenter(e.getRawX());
						this.gameManager.getStick().setCoordinateFront(e.getRawX() - this.gameManager.getStick().getLength()/2);
						this.gameManager.getStick().setCoordinateEnd(e.getRawX() + this.gameManager.getStick().getLength()/2);
						break;
					// if the stick had passed the left border of the screen before and now it's still trying to go to left side
					}else{
						break;
					}
				// if the stick tries to pass the right side of the screen
				}else if(this.gameManager.getStick().getCoordinateEnd() >= findViewById(R.id.gameScreen).getWidth()){
					// if the stick had passed the right border of the screen before and now it's coming back to legal borders
					if(e.getRawX() + this.gameManager.getStick().getLength()/2 < findViewById(R.id.gameScreen).getWidth()){
						this.gameManager.getStick().setCoordinateCenter(e.getRawX());
						this.gameManager.getStick().setCoordinateFront(e.getRawX() - this.gameManager.getStick().getLength()/2);
						this.gameManager.getStick().setCoordinateEnd(e.getRawX() + this.gameManager.getStick().getLength()/2);
						break;
					// if the stick had passed the right border of the screen before and now it's still trying to go to right side
					}else{
						break;
					}
				// if the stick stays between legal borders
				}else{
					this.gameManager.getStick().setCoordinateCenter(e.getRawX());
					this.gameManager.getStick().setCoordinateFront(e.getRawX() - this.gameManager.getStick().getLength()/2);
					this.gameManager.getStick().setCoordinateEnd(e.getRawX() + this.gameManager.getStick().getLength()/2);
					break;
				}
		}
		return true;
	}
	
	
	// getters for private attributes
	public GameManager getGameManager() {
		return gameManager;
	}
	public Paint getPaint() {
		return paint;
	}
	public Bitmap getImageStick() {
		return imageStick;
	}
	public Bitmap getImageBall() {
		return imageBall;
	}
	
	// setters for private attributes
	public void setGameManager(GameManager gameManager) {
		this.gameManager = gameManager;
	}
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	public void setImageStick(Bitmap imageStick) {
		this.imageStick = imageStick;
	}
	public void setImageBall(Bitmap imageBall) {
		this.imageBall = imageBall;
	}
	

}
