package com.zoe.androidgame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class SingleTouchTest extends Activity implements OnTouchListener {
	
	StringBuilder builder = new StringBuilder();
	TextView textView;
	@Override 
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		textView.setText("单点触碰测试~！！！");
		textView.setOnTouchListener(this);
		setContentView(textView);
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		builder.setLength(0);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			builder.append("按下,");
			break;
		case MotionEvent.ACTION_UP:
			builder.append("脱离,");
			break;
		case MotionEvent.ACTION_CANCEL:
			builder.append("cancel,");
			break;
		case MotionEvent.ACTION_MOVE:
			builder.append("移动,");
			break;
		}
		builder.append(event.getX());
		builder.append(",");
		builder.append(event.getY());
		String text = builder.toString();
		Log.d("TouchTest",text);
		textView.setText(text);
		return true;
	}

}
