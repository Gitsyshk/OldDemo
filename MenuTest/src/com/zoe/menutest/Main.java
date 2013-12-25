package com.zoe.menutest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add(Menu.NONE, Menu.FIRST + 1, 5, "删除").setIcon(
				android.R.drawable.ic_menu_delete);
		menu.add(Menu.NONE, Menu.FIRST + 2, 2, "保存").setIcon(
				android.R.drawable.ic_menu_edit);
		menu.add(Menu.NONE, Menu.FIRST + 3, 6, "帮助").setIcon(
				android.R.drawable.ic_menu_help);
		menu.add(Menu.NONE, Menu.FIRST + 4, 1, "添加").setIcon(
				android.R.drawable.ic_menu_add);
		menu.add(Menu.NONE, Menu.FIRST + 5, 4, "详细").setIcon(
				android.R.drawable.ic_menu_info_details);
		menu.add(Menu.NONE, Menu.FIRST + 6, 3, "发送").setIcon(
				android.R.drawable.ic_menu_send);
		// return true才会起作用
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			Toast.makeText(this, "删除菜单被点击了", Toast.LENGTH_LONG);
			break;
		case Menu.FIRST + 2:
			Toast.makeText(this, "保存菜单被点击了", Toast.LENGTH_LONG);
			break;
		case Menu.FIRST + 3:
			Toast.makeText(this, "帮助菜单被点击了", Toast.LENGTH_LONG);
			break;
		case Menu.FIRST + 4:
			Toast.makeText(this, "添加菜单被点击了", Toast.LENGTH_LONG);
			break;
		case Menu.FIRST + 5:
			Toast.makeText(this, "详细菜单被点击了", Toast.LENGTH_LONG);
			break;
		case Menu.FIRST + 6:
			Toast.makeText(this, "发送菜单被点击了", Toast.LENGTH_LONG);
			break;
		}
		return false;
	}

}
