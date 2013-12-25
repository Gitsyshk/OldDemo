package com.autoComplate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

public class AutoComplateTextViewActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		AutoCompleteAdater cursorAdapter = new AutoCompleteAdater(this, android.R.layout.simple_dropdown_item_1line, null, DBHelper.NAME, android.R.id.text1);
		// 设置输入一个字符就弹出提示列表(默认输入两个字符时才弹出提示)
		((AutoCompleteTextView) this.findViewById(R.id.autoCompleteTextView1)).setThreshold(1);
		((AutoCompleteTextView) this.findViewById(R.id.autoCompleteTextView1)).setAdapter(cursorAdapter);
	}
}