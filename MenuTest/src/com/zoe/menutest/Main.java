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
		menu.add(Menu.NONE, Menu.FIRST + 1, 5, "ɾ��").setIcon(
				android.R.drawable.ic_menu_delete);
		menu.add(Menu.NONE, Menu.FIRST + 2, 2, "����").setIcon(
				android.R.drawable.ic_menu_edit);
		menu.add(Menu.NONE, Menu.FIRST + 3, 6, "����").setIcon(
				android.R.drawable.ic_menu_help);
		menu.add(Menu.NONE, Menu.FIRST + 4, 1, "���").setIcon(
				android.R.drawable.ic_menu_add);
		menu.add(Menu.NONE, Menu.FIRST + 5, 4, "��ϸ").setIcon(
				android.R.drawable.ic_menu_info_details);
		menu.add(Menu.NONE, Menu.FIRST + 6, 3, "����").setIcon(
				android.R.drawable.ic_menu_send);
		// return true�Ż�������
		this.getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			Toast.makeText(this, "ɾ���˵��������", Toast.LENGTH_LONG);
			break;
		case Menu.FIRST + 2:
			Toast.makeText(this, "����˵��������", Toast.LENGTH_LONG);
			break;
		case Menu.FIRST + 3:
			Toast.makeText(this, "�����˵��������", Toast.LENGTH_LONG);
			break;
		case Menu.FIRST + 4:
			Toast.makeText(this, "��Ӳ˵��������", Toast.LENGTH_LONG);
			break;
		case Menu.FIRST + 5:
			Toast.makeText(this, "��ϸ�˵��������", Toast.LENGTH_LONG);
			break;
		case Menu.FIRST + 6:
			Toast.makeText(this, "���Ͳ˵��������", Toast.LENGTH_LONG);
			break;
		}
		return false;
	}

}
