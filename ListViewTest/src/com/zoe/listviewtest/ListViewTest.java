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
		"Android开发基础篇 第一讲",
		"Android开发基础篇 第二讲",
		"Android开发基础篇 第三讲",
		"Android开发基础篇 第四讲",
		"Android开发基础篇 第五讲",
		"Android开发基础篇 第六讲",
		"Android开发基础篇 第七讲",
		"Android开发基础篇 第八讲",
		"Android开发基础篇 第九讲"};
		listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,data));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_view_test, menu);
		return true;
	}

}
