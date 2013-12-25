package com.gobalapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Other extends Activity {
	private Myapp myApp;
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other);
		textView = (TextView)findViewById(R.id.msg);
		myApp = (Myapp)getApplication();
		textView.setText("app--->>"+myApp.getMsg());
	}
}
