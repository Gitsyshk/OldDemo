package com.zoe.handlertest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.ImageView;

public class HandlerTest extends Activity {
	private int[] imageIds = new int[] { R.drawable.lol1, R.drawable.lol2,
			R.drawable.lol3,R.drawable.lol4,R.drawable.lol5,R.drawable.lol6,R.drawable.lol7,
			R.drawable.lol8,R.drawable.lol9
	};
	private int currentImageId = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		final ImageView show = (ImageView) findViewById(R.id.show);
		final Handler myHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					show.setImageResource(imageIds[currentImageId++]);
					if (currentImageId >= 9) {
						currentImageId = 0;
					}
				}
			}
		};
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 0x123;
				myHandler.sendMessage(msg);				
			}
		}, 0, 1000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.handler_test, menu);
		return true;
	}

}
