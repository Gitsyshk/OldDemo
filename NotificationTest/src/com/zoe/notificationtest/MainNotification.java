package com.zoe.notificationtest;

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

public class MainNotification extends Activity {

	static final int Notification_ID = 0x1123;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button = (Button) findViewById(R.id.bn);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainNotification.this,
						OtherActivity.class);
				PendingIntent pi = PendingIntent.getActivity(
						MainNotification.this, 0, intent, 0);
				Notification notifi = new Notification();
				notifi.icon = R.drawable.away_thumb;
				notifi.tickerText = "启动其他Activity的通知";
				notifi.when = System.currentTimeMillis();
				notifi.defaults = Notification.DEFAULT_SOUND;
				notifi.defaults = Notification.DEFAULT_ALL;
				notifi.setLatestEventInfo(MainNotification.this, "普通通知",
						"单击查看", pi);
				// 发送通知
				NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				notificationManager.notify(Notification_ID, notifi);
			}
		});
		Button del = (Button) findViewById(R.id.del);
		del.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				//取消通知
				notificationManager.cancel(Notification_ID);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_notification, menu);
		return true;
	}

}
