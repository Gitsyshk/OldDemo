package com.zoe.listviewtest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListViewTest extends Activity {
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listView = (ListView)findViewById(R.id.list);
		 String[] data ={
		"Android��������ƪ ��һ��",
		"Android��������ƪ �ڶ���",
		"Android��������ƪ ������",
		"Android��������ƪ ���Ľ�",
		"Android��������ƪ ���彲",
		"Android��������ƪ ������",
		"Android��������ƪ ���߽�",
		"Android��������ƪ �ڰ˽�",
		"Android��������ƪ �ھŽ�"};
		listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,data));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view_test, menu);
		return true;
	}

}
