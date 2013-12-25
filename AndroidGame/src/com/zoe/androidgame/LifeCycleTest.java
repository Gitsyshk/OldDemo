package com.zoe.androidgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class LifeCycleTest extends Activity {

	StringBuilder builder = new StringBuilder();
	TextView textView;

	private void log(String text) {
		Log.d("LifeCycleTest", text);
		builder.append(text);
		builder.append("\n");
		textView.setText(builder.toString());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText(builder.toString());
		setContentView(textView);
		log("´´½¨create");
	}

	@Override
	protected void onResume() {
		super.onResume();
		log("»Ö¸´resume");
	}

	@Override
	protected void onPause() {
		super.onPause();
		log("ÔÝÍ£pause");
		
		if(isFinishing())
		{
			log("finishing");
		}
	}
}