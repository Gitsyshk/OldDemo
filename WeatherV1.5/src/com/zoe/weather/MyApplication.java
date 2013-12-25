package com.zoe.weather;

import android.app.Application;

import com.zoe.data.MyData_1;
import com.zoe.data.MyData_2;
import com.zoe.data.MyData_3;

public class MyApplication extends Application {
	public MyData_1 myData_1;
	public MyData_2 myData_2;
	public MyData_3 myData_3;
	public String city,city_id,weather,temp,temp1,wind,sd,uv,tour;
	
	@Override
	public void onCreate() {
		super.onCreate();
		myData_1 = new MyData_1();
		myData_2 = new MyData_2();
		myData_3 = new MyData_3();
	}
}
