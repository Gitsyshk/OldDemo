package com.zoe.jsontest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {
	private Button button;
	private TextView textView;
	private MyApplication application;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button = (Button)findViewById(R.id.button);
		textView = (TextView)findViewById(R.id.textView);
		application = (MyApplication)getApplication();
		
		try {
			Socket socket = new Socket("192.168.1.113",12345);
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line = reader.readLine();
			System.out.println(line);
			//JsonUtils.parseJson(line, application);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		button.setOnClickListener(new OnClickListener() {		
//			@Override
//			public void onClick(View v) {		
//				textView.setText(
//						application.data.getKey1()+":"+application.data.getValue1()+"\n"
//						+application.data.getKey2()+":"+application.data.getValue2()+"\n"
//						+application.data.getKey3()+":"+application.data.getValue3()+"\n"
//						+application.data.getKey4()+":"+application.data.getValue4()+"\n"
//						);				
//			}
//		});
	}
}
