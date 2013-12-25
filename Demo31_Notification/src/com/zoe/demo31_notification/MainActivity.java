package com.zoe.demo31_notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	private Button button;
	static final int NOTIFICATION_ID = 0x1123;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (Button)findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,OtherActivity.class);
				PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
				Notification notification = new Notification();
				notification.icon = R.drawable.ic_launcher;
				notification.tickerText = "通知1";
				notification.when = System.currentTimeMillis();
				notification.defaults = Notification.DEFAULT_ALL;
				notification.setLatestEventInfo(MainActivity.this, "普通通知", "点击查看", pi);
				NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				notificationManager.notify(NOTIFICATION_ID,notification);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
