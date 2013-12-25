package com.zoe.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	EditText userName;
	EditText passWord;
	Button enter;
	Button esc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.login);

		userName = (EditText) findViewById(R.id.userName);
		passWord = (EditText) findViewById(R.id.passWord);
		enter = (Button) findViewById(R.id.enter);
		esc = (Button) findViewById(R.id.esc);

		enter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (userName.getText().toString().trim().equals("z")
						&& passWord.getText().toString().trim().equals("1")) {
					Intent intent = new Intent();
					try {
						intent.setClass(Login.this,
								Class.forName("com.zoe.client.Main"));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					startActivity(intent);
				} else {
					passWord.setText("");
					Toast.makeText(Login.this, "’À∫≈ªÚ√‹¬Î≤ª¥Ê‘⁄£¨«Î÷ÿ ‘°£",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		esc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				userName.setText("");
				passWord.setText("");

			}
		});
	}
}
