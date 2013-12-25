package com.ideaspace.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import com.ideaspace.data.DataTool;

import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	private boolean quit = true;
	private Socket socket = null;
	private BufferedReader br = null;
	private Binder binder = new Binder();
	private MyApplication application;
	@Override
	public IBinder onBind(Intent intent) {
		try {
			socket = new Socket(application.IP, 30000);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			return null;
		} catch (IOException e) {
			return null;
		}	
		new Thread(){
			String str;
			@Override
			public void run() {
				try {
					while ((str = br.readLine()) != null && quit) {
						application.dataModel = DataTool.formJson(str.toString());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}.start();
		return binder;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		application = (MyApplication)getApplication();
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		quit = false;
	}
}
