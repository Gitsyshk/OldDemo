package com.zoe.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.zoe.data.MyData_1;
import com.zoe.data.MyData_2;
import com.zoe.data.MyData_3;

public class LoadingActivity extends Activity implements Runnable {
	MyApplication application;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.loading);
		application = (MyApplication) getApplication();
		new Thread(LoadingActivity.this).start();
	}

	@Override
	public void run() {
		application.myData_1 = new MyData_1();
		application.myData_2 = new MyData_2();
		application.myData_3 = new MyData_3();
		application.preferences = getSharedPreferences("zoe", MODE_PRIVATE);
		application.city = new String[10];
		for(int i =0 ;i<10;i++)
		{
			application.city[i] = application.preferences.getString("city"+i, null);
		}
		application.city[0] = application.city[0] == null ? "±±¾©" : application.city[0];
		String city_id_temp = application.preferences
				.getString("city_id", null);
		application.city_id = application.city_id == null ? "101010100"
				: city_id_temp;
		application.refresh = new Refresh(application);
		application.refresh.start();
		application.refresh.setFlag();
		Intent intent = new Intent();
		intent.setClass(this, MainActivity.class);
		try {
			Thread.sleep(2000L);
		} catch (Exception e) {

		}
		startActivity(intent);
		finish();
	}
}
