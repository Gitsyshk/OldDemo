package com.example.link;

import com.example.demo27_link.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Linnk extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.linnk, menu);
		return true;
	}

}
