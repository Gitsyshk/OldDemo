package com.zoe.intenttest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 按照main.xml來渲染用戶界面
		setContentView(R.layout.main);

		// 找到存放电话号码的可编辑文本框
		final EditText PhoneNumberEditText = (EditText) findViewById(R.id.PhoneNumberEditText);

		// 找到拨号按钮
		Button button = (Button) findViewById(R.id.Button01);
		
		// 为拨号按钮设置一个点击事件观察者
		button.setOnClickListener(new Button.OnClickListener() {
			//实现监听器接口的匿名内部类,其中监听器本身是View类的内部接口

			//实现接口必须实现的onClick方法
			@Override
			public void onClick(View v) {
				// 获得可编辑文本框中的值，也就是电话号码
				String phoneNumber = PhoneNumberEditText.getText().toString();
				// new Intent(行为，数据)，其中action_dial是拨号行为，数据是电话号码
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri
						.parse("tel://" + phoneNumber));
				// 去调用那些可以处理拨号行为的Activity
				startActivity(intent);
			}
		});
		
	}
}