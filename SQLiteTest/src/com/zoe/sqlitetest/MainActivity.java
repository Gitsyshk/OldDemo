package com.zoe.sqlitetest;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button bn;
	TextView textView;
	AutoCompleteTextView autoCompleteTextView;
	DataBaseHelper myDbHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textView = (TextView) findViewById(R.id.text);
		autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
		myDbHelper = new DataBaseHelper(this);
		try {
		
			myDbHelper.createDataBase();
			myDbHelper.openDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		AutoCompleteAdater cursorAdapter = new AutoCompleteAdater(this, android.R.layout.simple_dropdown_item_1line, null, "name", android.R.id.text1,myDbHelper);
		autoCompleteTextView.setAdapter(cursorAdapter);
	
		Cursor cursor = myDbHelper.getReadableDatabase().rawQuery(
				"select * from user", null);
		while (cursor.moveToNext()) {
			String name = cursor.getString(cursor.getColumnIndex("name"));
			String id = cursor.getString(cursor.getColumnIndex("id"));
			textView.append("\t"+name + "\t\t~\t\t " + id+"\n");
			// int id = cursor.getInt(INDEX);
			// byte[] val = cursor.getBlob(cursor.getColumnIndex("name"));
			// try {
			// String name =new String(val,"utf-8");
			// name.replace("��", "");
			// textView.setText(name+name.equals("北京"));
			// } catch (UnsupportedEncodingException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }//设置编码方式
			// Log.d("msg", ""+id);
			//
		}

	}

}
