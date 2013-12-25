package com.zoe.client;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.ToggleButton;

public class Main extends Activity {
	RatingBar ratingBar;
	ToggleButton toggleButton;
	State state;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		
		state = new State();
		toggleButton = (ToggleButton)findViewById(R.id.toggleButton);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
	}
}
