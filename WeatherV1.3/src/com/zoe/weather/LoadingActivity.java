package com.zoe.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

public class LoadingActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.loading);
		new InitThread().start();
	}

	public Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 0x123) {
				startActivity(new Intent(getApplication(), MainActivity.class));
				LoadingActivity.this.finish();
			}
		}

	};

	public class InitThread extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(1500);
				Message msg = new Message();
				msg.what = 0x123;
				mHandler.sendMessage(msg);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
