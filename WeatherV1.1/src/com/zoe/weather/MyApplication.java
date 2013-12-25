package com.zoe.weather;

import android.app.Application;

import com.zoe.date.MyData_1;
import com.zoe.date.MyData_2;

public class MyApplication extends Application {
	public MyData_1 myData_1;
	public MyData_2 myData_2;
	public String city;
	public String city_id;
	
	@Override
	public void onCreate() {
		super.onCreate();
		myData_1 = new MyData_1();
		myData_2 = new MyData_2();
	}
}
