package com.ideaspace.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.ideaspace.data.DataTool;

public class MyService extends Service {
	private boolean quit = true;
	private Socket socket = null;
	private BufferedReader br = null;
	private Binder binder = new Binder();
	static final int NOTIFICATION_ID = 0x1233;
	private MyApplication application;
	private Intent intent;
	private PendingIntent pi;
	private Notification notification;
	private NotificationManager notificationManager;

	@Override
	public IBinder onBind(Intent intent) {
		try {
			Log.d("msg", application.IP);
			socket = new Socket(application.IP, 30000);
			br = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
		
		} catch (IOException e) {
			return null;
		}
		new Thread() {
			String str;

			@Override
			public void run() {
				try {
					while ((str = br.readLine()) != null && quit) {
						application.dataModel = DataTool.formJson(str
								.toString());
						if (application.dataModel.getValue3() > 40.0) {
							notification.when = System.currentTimeMillis();
							notificationManager.notify(NOTIFICATION_ID,
									notification);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}.start();
		return binder;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate() {
		super.onCreate();
		intent = new Intent(MyService.this, MainActivity.class);
		pi = PendingIntent.getActivity(MyService.this, 0, intent, 0);
		notification = new Notification();
		notification.icon = R.drawable.notify;
		notification.defaults = Notification.DEFAULT_ALL;
		notification.setLatestEventInfo(MyService.this, "警告", "危险气体浓度超标", pi);
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		application = (MyApplication) getApplication();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		quit = false;
	}
}
