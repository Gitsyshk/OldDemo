package com.zoe.httptest;

import android.app.Application;

import com.zoe.data.MyData;

public class MyApplication extends Application {
	public MyData myData;
	@Override
	public void onCreate() {
		super.onCreate();
		myData = new MyData();
	}
}
