package com.zoe.httptest;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView textView1;
	private TextView textView2;
	private TextView city;
	private ImageView imageView;
	private MyApplication application;
	private LinearLayout layout;
	private static final int[] color = new int[] { R.color.c1_1, R.color.c1_2,
			R.color.c1_3, R.color.c1_4, R.color.c2_1, R.color.c2_2,
			R.color.c2_3, R.color.c2_4, R.color.c3_1, R.color.c3_2,
			R.color.c3_3, R.color.c3_4, R.color.c4_1, R.color.c4_2,
			R.color.c4_3, R.color.c4_4, R.color.c5_1, R.color.c5_2,
			R.color.c5_3, R.color.c5_4, R.color.c6_1, R.color.c6_2,
			R.color.c6_3, R.color.c6_4, };
	int temp = 0;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main_layout);

		application = (MyApplication) getApplication();
		layout = (LinearLayout) findViewById(R.id.layout);
		textView1 = (TextView) findViewById(R.id.textView1);
		textView2 = (TextView) findViewById(R.id.textView2);
		city = (TextView) findViewById(R.id.city);
		imageView = (ImageView) findViewById(R.id.imageView);

		Renew renew = new Renew(application);
		renew.start();
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					// textView.setText("城       市\t:"
					// + application.myData.weatherinfo.city + "\t\n"
					// + "最 高 温\t:" + application.myData.weatherinfo.temp1
					// + "\n" + "最 低 温\t:"
					// + application.myData.weatherinfo.temp2 + "\n"
					// + "天      气 \t:"
					// + application.myData.weatherinfo.weather + "\t\n"
					// + "PTime\t:" + application.myData.weatherinfo.ptime
					// + "\t\n");
					city.setText(application.myData.weatherinfo.city);
					textView1.setText(application.myData.weatherinfo.weather);
					textView2.setText(application.myData.weatherinfo.temp1
							+ "~" + application.myData.weatherinfo.temp2);
					if (application.myData.weatherinfo.weather.equals("晴"))
						imageView.setImageResource(R.drawable.sunny);
					else if (application.myData.weatherinfo.weather
							.equals("多云"))
						imageView.setImageResource(R.drawable.cloudy);
					else if (application.myData.weatherinfo.weather
							.equals("阵雨"))
						imageView.setImageResource(R.drawable.thunder_shower);
					else if (application.myData.weatherinfo.weather
							.equals("晴转小雨"))
						imageView.setImageResource(R.drawable.overcast);
					else if (application.myData.weatherinfo.weather
							.equals("小雨"))
						imageView.setImageResource(R.drawable.light_rain);
				}

			}
		};
		layout.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				temp++;
				if (temp >= color.length)
					temp = 0;
				Log.d("msg", color[temp] + "");
				layout.setBackgroundResource(color[temp]);
			}
		});
		new Timer().schedule(new TimerTask() {

			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 0x123;
				handler.sendMessage(msg);

			}
		}, 0, 1000);
	}
}
