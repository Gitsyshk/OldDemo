package com.zoe.servicetest;

import android.app.Application;

public class MyAppliction extends Application {
	String MyData;
	@Override
	public void onCreate() {
		MyData = "≥ı ºªØ";
		super.onCreate();
	}
	public String getMyData() {
		return MyData;
	}
	public void setMyData(String myData) {
		MyData = myData;
	}
}
