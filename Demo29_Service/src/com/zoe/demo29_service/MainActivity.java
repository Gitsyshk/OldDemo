package com.zoe.demo29_service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button bind,unbind,get;
	FirstService.MyBinder binder;
	private ServiceConnection conn = new ServiceConnection()
	{	@Override
		public void onServiceDisconnected(ComponentName name) {

			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bind = (Button)findViewById(R.id.bind);
		unbind = (Button)findViewById(R.id.unbind);
		get = (Button)findViewById(R.id.get);
		final Intent intent = new Intent();
		intent.setAction("com.zoe.service.FIRST_SERVICE");
	}
}
