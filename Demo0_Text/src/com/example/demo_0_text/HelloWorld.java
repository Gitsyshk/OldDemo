package com.example.demo_0_text;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HelloWorld extends Activity {
	// 当第一次创建该Activity时回调该方法
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置使用main.xml文件定义的页面布局
		setContentView(R.layout.main);
		// 获取UI界面中ID为R.id.ok的按钮
		Button bn = (Button) findViewById(R.id.ok);
		// 为按钮绑定一个单击事件的监听器
		bn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 获取UI界面中ID为R.id.show的文本框
				final TextView show = (TextView) findViewById(R.id.show);
				// 改变文本框的文本内容
				show.setText("Hello Android~" + new java.util.Date());
			}
		});
	}
}
