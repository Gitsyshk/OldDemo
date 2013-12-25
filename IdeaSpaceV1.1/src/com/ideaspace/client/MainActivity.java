package com.ideaspace.client;

import java.io.IOException;
import java.net.Socket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.TextView;

import com.ideaspace.data.DataTool;

public class MainActivity extends Activity {

	private MyApplication application;
	private Handler myHandler;
	private TextView textView1, textView2, textView3;
	private Socket socket;
	public String IP = "192.168.0.103";
	private int port = 30000;
	private Thread thread;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		application = (MyApplication) getApplication();
		textView1 = (TextView) findViewById(R.id.key1);
		textView2 = (TextView) findViewById(R.id.key2);
		textView3 = (TextView) findViewById(R.id.key3);
		myHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x1233) {
					application.dataModel = DataTool.formJson(msg.obj.toString());
					textView1.setText("温度:\t"+application.dataModel.getValue1());
					textView2.setText("湿度：\t"+application.dataModel.getValue2());
					textView3.setText("危险气体浓度：\t"+application.dataModel.getValue3());			
				}
			}
		};
	}

	@Override
	protected void onResume() {
		super.onResume();
		try {
			thread = new Thread(new ClientThread(IP, port, myHandler));
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		try {
			if(socket !=  null)
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
