package com.zoe.textView;

import java.lang.reflect.Field;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.view.Menu;
import android.widget.TextView;

public class Main extends Activity {
	private TextView textView;

	public int getResourceId(String name) {
		try {
			Field field = R.drawable.class.getField(name);
			return Integer.parseInt(field.get(null).toString());

		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textView = (TextView) findViewById(R.id.textview);
		textView.setTextColor(Color.BLACK);
		textView.setBackgroundColor(Color.WHITE);
		textView.setTextSize(20);
		String html = "图像1<img src='a2'/>图像2<img src='a1'>"
				+ "图像3<img src='a3'><p>"
				+ "图像4<a href='http://www.baidu.com'><img src='a4'></a>";
		CharSequence charSequence = Html.fromHtml(html, new ImageGetter() {
			// 获得系统资源的信息，比如图片信息
			public Drawable getDrawable(String source) {
				Drawable drawable = getResources().getDrawable(
						getResourceId(source));
				if (source.equals("a2")) {
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2,
							drawable.getIntrinsicHeight() / 2);
				} else {
					drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
							drawable.getIntrinsicHeight());
				}
				return null;
			}
		}, null);
		textView.setText(charSequence);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
