package com.zoe.textview;

import android.os.Bundle;
import android.app.Activity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.widget.TextView;

public class Main extends Activity {
	private TextView textView1,textView2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		textView1 = (TextView)findViewById(R.id.textview1);
		textView2 = (TextView)findViewById(R.id.textview2);
		String html = "<font color='red'>I love android </font><br>;" ;
		html += "<font color='#0000ff'><big><i>I love China</i></big></font><p>";
		html += "<big><a href='http://www.baidu/com'>�ٶ�</a><big>";
		CharSequence charSequence = Html.fromHtml(html);
		textView1.setText(charSequence);
		textView1.setMovementMethod(LinkMovementMethod.getInstance());
		
		String text = "�ҵ�URL��http://www.sina.com\n";
		text += "�ҵ�email:zt1991616@yahoo.com.cn";
		text += "�ҵĵ绰:15216105617";
		textView2.setText(text);
		textView2.setMovementMethod(LinkMovementMethod.getInstance());
	}
}
