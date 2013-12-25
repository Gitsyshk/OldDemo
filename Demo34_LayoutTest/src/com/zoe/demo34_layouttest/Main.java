package com.zoe.demo34_layouttest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.TextView;

public class Main extends Activity {

	private int currentColor = 0;
	final int[] colors = new int[] { R.color.color1, R.color.color2,
			R.color.color3, R.color.color4, R.color.color5, R.color.color6,
			R.color.color7 };
	final int[] names = new int[]
			{
			R.id.View01,
			R.id.View02,
			R.id.View03,
			R.id.View04,
			R.id.View05,
			R.id.View06,
			R.id.View07
			};
	TextView[] views = new TextView[7];

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_1);
		for(int i = 0;i < 7; i++)
		{
			views[i] = (TextView)findViewById(names[i]);
		}
		final Handler handler = new Handler()
		{
			public void handleMessage(Message msg) {
				if(msg.what == 0x1234)
				{
					for(int i = 0; i < 7 - currentColor;i++)
					{
						views[i].setBackgroundResource(colors[i+currentColor]);
					}
					for(int i = 7-currentColor,j=0;i<7;i++,j++){
						views[i].setBackgroundResource(colors[j]);
					}
				}
				super.handleMessage(msg);
			};
		};
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				currentColor ++;
				if(currentColor >= 6)
				{
					currentColor = 0;
				}
				Message m = new Message();
				m.what = 0x1234;
				handler.sendMessage(m);
			}
		}, 0,200);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
