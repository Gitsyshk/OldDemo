package com.zoe.netwrok;

import java.io.IOException;
import java.net.Socket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.RatingBar;
import android.widget.TextView;

public class Main extends Activity {

	private MyApplication application;
	private Handler myHandler;
	private TextView textView1, textView2, textView3, textView4;
	private Socket socket;
	private RatingBar ratingBar;
	public String IP = "192.168.1.112";
	private int port = 30000;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		application = (MyApplication) getApplication();
		textView1 = (TextView) findViewById(R.id.key1);
		textView2 = (TextView) findViewById(R.id.key2);
		textView3 = (TextView) findViewById(R.id.key3);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		application.data.setKey1("温度");
		application.data.setKey2("湿度");
		application.data.setKey3("危险气体浓度");
		application.data.setKey4("预留项");
		myHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x1233) {
					JsonUtils.parseJson(msg.obj.toString(), application);
					ratingBar.setRating(Integer.parseInt(application.data
							.getValue4()) % 4);
					textView1.setText(application.data.getKey1() + ":"
							+ application.data.getValue1());
					textView2.setText(application.data.getKey2() + ":"
							+ application.data.getValue2());
					textView3.setText(application.data.getKey3() + ":"
							+ application.data.getValue3());
					textView4.setText(application.data.getKey4() + ":"
							+ application.data.getValue4());
				}
			}
		};
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			new Thread(new ClientThread(IP, port, myHandler)).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		try {
			if(socket != null)
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
