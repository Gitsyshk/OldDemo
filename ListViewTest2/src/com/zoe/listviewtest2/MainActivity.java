package com.zoe.listviewtest2;

import com.zoe.listviewtest1.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		ListView listView = (ListView) findViewById(R.id.listView);
		
		TextView header = new TextView(this);
		header.setText("只是一个HanderView");
		header.setTextColor(Color.RED);
		
		TextView footer = new TextView(this);
		footer.setText("只是一个FooterView");
		footer.setTextColor(Color.RED);
			
		listView.addHeaderView(header);
        listView.addFooterView(footer);
		
		String[] arr = { "第一个", "第二个", "第三个", "第四个","第五个","第六个","第七个","第八个","第九个","第十个","第十一个" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,arr);
		
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
