package com.zoe.servicetest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	private Binder binder;
	private MyAppliction myAppliction;
	private int i = 0;
	@Override
	public void onCreate() {
		super.onCreate();
		myAppliction = (MyAppliction)getApplication();
		myAppliction.setMyData("初始化成功");
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				i++;
				myAppliction.setMyData(""+i);
			}
		}, 0, 1000);
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return binder;
	}
}
