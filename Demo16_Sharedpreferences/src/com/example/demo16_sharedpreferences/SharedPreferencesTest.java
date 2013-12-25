package com.example.demo16_sharedpreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SharedPreferencesTest extends Activity {

	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		preferences = getSharedPreferences("zoe", MODE_PRIVATE);
		editor = preferences.edit();
		Button read = (Button) findViewById(R.id.read);
		Button write = (Button) findViewById(R.id.write);
		read.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String time = preferences.getString("time", null);
				int randNum = preferences.getInt("random", 0);
				String result = time == null ? "您暂时未写入数据" : "写入时间为：" + time
						+ "\n上次生成的随机数为:" + randNum;
				Toast.makeText(SharedPreferencesTest.this, result, 5000).show();
			}
		});
		write.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日"
						+ "hh:mm:ss");
				editor.putString("time", sdf.format(new Date()));
				editor.putInt("random", (int) (Math.random() * 100));
				editor.commit();

			}
		});
	}

}
