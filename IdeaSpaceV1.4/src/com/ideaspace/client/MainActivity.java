package com.ideaspace.client;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private MyApplication application;
	private Handler myHandler;
	private TextView textView1, textView2, textView3;
	private EditText editText;
	private Button enter;
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("连接上了");
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("断开了");
		}
	};

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		application = (MyApplication) getApplication();
		textView1 = (TextView) findViewById(R.id.key1);
		textView2 = (TextView) findViewById(R.id.key2);
		textView3 = (TextView) findViewById(R.id.key3);
		editText = (EditText) findViewById(R.id.edit);
		enter = (Button) findViewById(R.id.enter);
		final Intent intent = new Intent();
		intent.setAction("com.zoe.service.MY_SERVICE");
		enter.setOnClickListener(new OnClickListener() {
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				application.IP = editText.getText().toString().trim();
				if (!bindService(intent, conn, Service.BIND_AUTO_CREATE)) {
					Toast.makeText(MainActivity.this, "连接失败请重试！", 1000).show();
				}

				else {
					enter.setVisibility(0x00000004);
					editText.setVisibility(0x00000004);
				}

			}
		});
		myHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x1233) {
					textView1.setText("温度:\t"
							+ application.dataModel.getValue1() + "℃");
					textView2.setText("湿度：\t"
							+ application.dataModel.getValue2() + "%");
					textView3.setText("危险气体浓度：\t"
							+ application.dataModel.getValue3() + "%");
				}
			}
		};
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 0x1233;
				myHandler.sendMessage(msg);
			}
		}, 0, 500);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onPause() {
		super.onPause();

	}

}
