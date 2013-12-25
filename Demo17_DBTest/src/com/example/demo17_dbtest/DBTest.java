package com.example.demo17_dbtest;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DBTest extends Activity {

	SQLiteDatabase db;
	Button bn = null;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()
				+ "/my.db3", null);
		listView = (ListView) findViewById(R.id.show);
		bn = (Button) findViewById(R.id.ok);
		bn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String title = ((EditText) findViewById(R.id.title)).getText()
						.toString();
				String content = ((EditText) findViewById(R.id.content))
						.getText().toString();
				try {
					insertData(db, title, content);
					Cursor cursor = db.rawQuery("select * from new_inf", null);
					inflateList(cursor);
				} catch (SQLiteException se) {

				}
			}

		});
	}

	private void insertData(SQLiteDatabase db, String title, String content) {
		db.execSQL("insert into news_lnf values(null,?,?)", new String[] {
				title, content });

	}

	private void inflateList(Cursor cursor)
	{
		//填充SimpleCursorAdapter
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
			DBTest.this , R.layout.line, cursor 
			, new String[]{"news_title" , "news_content"}
			, new int[]{R.id.my_title , R.id.my_content});
		//显示数据
		listView.setAdapter(adapter);
	}
	@Override
	public void onDestroy()
	{
		super.onDestroy();
		//退出程序时关闭SQLiteDatabase
		if (db != null && db.isOpen())
		{
			db.close();
		}
	}
}
