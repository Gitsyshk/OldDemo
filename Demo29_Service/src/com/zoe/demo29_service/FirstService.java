package com.zoe.demo29_service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class FirstService extends Service {
	private int count;
	private boolean quit;
	public MyBinder binder = new MyBinder();
	class MyBinder extends Binder
	{
		public int getCount(){
			return count;
		}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		System.out.println("Service is Binded");
		return null;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		System.out.println("Service is Create!");
		new Thread(){
			public void run() {
				while (!quit) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						e.printStackTrace();
					}					
					count++;
				}
			};
		}.start();
	}
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("Service is Unbind");
		return super.onUnbind(intent);
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		this.quit = true;
		System.out.println("Service is Destory");
	}
}
