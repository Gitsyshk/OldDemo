package com.zoe.servicetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {
	private TextView textView;
	Button open;
	Button close;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textView = (TextView)findViewById(R.id.textView);
		open = (Button)findViewById(R.id.bn1);
		close = (Button)findViewById(R.id.bn2);
		final Intent intent = new Intent();
		intent.setAction("com.zoe.service.MYSERVICE");
		
		open.setOnClickListener(new OnClickListener() {		
			@Override
			public void onClick(View v) {
				startService(intent);
				textView.setText(((MyAppliction)getApplication()).getMyData());
			}
		});
		close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				stopService(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
