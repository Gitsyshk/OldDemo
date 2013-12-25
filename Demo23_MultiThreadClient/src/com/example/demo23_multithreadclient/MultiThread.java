package com.example.demo23_multithreadclient;

import java.io.OutputStream;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MultiThread extends Activity {

	EditText input,show;
	Button send;
	OutputStream os;
	Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		input = (EditText)findViewById(R.id.input);
		send = (Button)findViewById(R.id.send);
		show = (EditText)findViewById(R.id.show);
		Socket s;
		handler = new Handler()
		{
			@Override
			public void handleMessage(Message msg)
			{
				if(msg.what == 0x123)
				{
					show.append("\n" + msg.obj.toString());
				}
			}
		};
		try
		{
			s = new Socket("192.168.1.88", 30000);
			// 客户端启动ClientThread线程不断读取来自服务器的数据
			new Thread(new ClientThread(s, handler)).start(); // ①
			os = s.getOutputStream();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		send.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				try
				{
					os.write((input.getText().toString() + "\r\n")
						.getBytes("utf-8"));
					input.setText("");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.multi_thread, menu);
		return true;
	}

}
