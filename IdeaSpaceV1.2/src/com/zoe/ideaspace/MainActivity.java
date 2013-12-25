package com.zoe.ideaspace;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView textView1, textView2, textView3;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView1 = (TextView) findViewById(R.id.key1);
		textView2 = (TextView) findViewById(R.id.key2);
		textView3 = (TextView) findViewById(R.id.key3);
		final Intent intent = new Intent();
		intent.setAction("com.zoe.service.MYSERVICE");
		startService(intent);
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					textView1
							.setText(((MyApplication) getApplication()).dataModel
									.getValue1());
					textView2
							.setText(((MyApplication) getApplication()).dataModel
									.getValue2());
					textView3
							.setText(((MyApplication) getApplication()).dataModel
									.getValue3());
				}
			}
		};
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendEmptyMessage(0x123);
			}
		}, 0, 100);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
