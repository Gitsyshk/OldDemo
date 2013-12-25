package com.zoe.ideaspace;

import java.io.IOException;
import java.net.UnknownHostException;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
	private String IP = "192.168.0.103";
	private int port = 30000;
	@Override
	public void onCreate() {
		super.onCreate();
		try {
			new Thread(new ClientThread(IP, port,(MyApplication)getApplication())).start();
			((MyApplication)getApplication()).dataModel.setValue1(20+"");
			((MyApplication)getApplication()).dataModel.setValue1(20+"");
			((MyApplication)getApplication()).dataModel.setValue1(20+"");			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
