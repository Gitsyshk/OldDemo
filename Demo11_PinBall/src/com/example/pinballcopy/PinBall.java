package com.example.pinballcopy;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

@SuppressLint("HandlerLeak")
public class PinBall extends Activity {
	private int tableWidth;
	private int tableHeight;
	private int racketY;
	private final int RACKET_HEIGHT = 20;
	private final int RACKET_WIDTH = 70;
	private final int BALL_SIZE = 12;
	private int ySpeed = 10;
	Random rand = new Random();
	private double xyRate = rand.nextDouble() - 0.5;
	private double xSpeed = (int) (ySpeed * xyRate * 2);
	private int ballX = rand.nextInt(200) + 20;
	private int ballY = rand.nextInt(10) + 20;
	private int racketX = rand.nextInt(200);
	private boolean isLose = false;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		final GameView gameView = new GameView(this);
		setContentView(gameView);
		WindowManager windowManager = getWindowManager();
		Display display = windowManager.getDefaultDisplay();
		tableHeight = display.getHeight();
		tableWidth = display.getWidth();
		racketY = tableHeight - 80;
		final Handler handler = new Handler() {
			@SuppressWarnings("unused")
			public void hanldeMessage(Message msg) {
				if (msg.what == 0x123) {
					gameView.invalidate();
				}
			}
		};
		gameView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				float x = event.getX();
				float y = event.getY();
				if (x > racketX) {
					racketX += 10;
					if (racketX > x)
						racketX = (int) x;
				} else if (x < racketX) {
					racketX = -10;
					if (racketX < x)
						racketX = (int) x;
				}
				if (y > racketY) {
					racketY += 10;
					if (racketY > y)
						racketY = (int) y;
				} else if (y < racketY) {
					racketY = -10;
					if (racketY < y)
						racketY = (int) y;
				}
				gameView.invalidate();
				return true;
			}
		});
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				if (ballX <= 0 || ballX >= tableWidth - BALL_SIZE) {
					xSpeed = -xSpeed;
				}
				if (ballY >= racketY - BALL_SIZE
						&& (ballX < racketX || ballX > racketX + RACKET_WIDTH)) {
					timer.cancel();
					isLose = true;
				} else if (ballY <= 0
						|| (ballY >= racketY - BALL_SIZE && ballX > racketX && ballX <= racketX
								+ RACKET_WIDTH)) {
					ySpeed = -ySpeed;
				}
				ballX+=ySpeed;
				ballY+=xSpeed;
				handler.sendEmptyMessage(0x123);
			}

		}, 0, 100);
	}

	class GameView extends View {

		public GameView(Context context) {
			super(context);
			setFocusable(true);
		}

		@SuppressLint("DrawAllocation")
		public void onDraw(Canvas canvas) {
			Paint paint = new Paint();
			paint.setStyle(Paint.Style.FILL);
			if (isLose) {
				paint.setColor(Color.RED);
				paint.setTextSize(40);
				canvas.drawText("ÓÎÏ·½áÊø", 50, 200, paint);
			} else {
				paint.setColor(Color.rgb(240, 240, 80));
				canvas.drawCircle(ballX, ballY, BALL_SIZE, paint);
				paint.setColor(Color.rgb(80, 80, 200));
				canvas.drawRect(racketX, racketY, racketX + RACKET_WIDTH,
						racketY + RACKET_HEIGHT, paint);
			}
		}
	}
}
