package com.zoe.menutest1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	private static final int MENU_ITEM = Menu.FIRST;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		int gtoupId = 0;
		int menuItemId = MENU_ITEM;
		int menuItemOrder = Menu.NONE;
		menu.add(gtoupId,menuItemId,menuItemOrder,"CheckBox").setCheckable(true);
		return true;
	}

}
