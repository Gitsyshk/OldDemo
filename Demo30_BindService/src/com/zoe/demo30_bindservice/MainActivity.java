package com.zoe.demo30_bindservice;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button bind,unbind,get;
	BindService.MyBinder binder;
	private ServiceConnection conn = new ServiceConnection(){
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
				//断开时调用
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			binder = (BindService.MyBinder)service;
			//连接成功时调用
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bind = (Button)findViewById(R.id.bind);
		unbind = (Button)findViewById(R.id.button1);
		get = (Button)findViewById(R.id.button2);
		final Intent intent = new Intent();
		intent.setAction("com.zoe.service.BIND_SERVICE");
		bind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				bindService(intent, conn, Service.BIND_AUTO_CREATE);		
			}
		});
		unbind.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				unbindService(conn);
				
			}
		});
		get.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "count:"+binder.getCount(), 4000).show();
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
