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
		
		// ����main.xml����Ⱦ�Ñ�����
		setContentView(R.layout.main);

		// �ҵ���ŵ绰����Ŀɱ༭�ı���
		final EditText PhoneNumberEditText = (EditText) findViewById(R.id.PhoneNumberEditText);

		// �ҵ����Ű�ť
		Button button = (Button) findViewById(R.id.Button01);
		
		// Ϊ���Ű�ť����һ������¼��۲���
		button.setOnClickListener(new Button.OnClickListener() {
			//ʵ�ּ������ӿڵ������ڲ���,���м�����������View����ڲ��ӿ�

			//ʵ�ֽӿڱ���ʵ�ֵ�onClick����
			@Override
			public void onClick(View v) {
				// ��ÿɱ༭�ı����е�ֵ��Ҳ���ǵ绰����
				String phoneNumber = PhoneNumberEditText.getText().toString();
				// new Intent(��Ϊ������)������action_dial�ǲ�����Ϊ�������ǵ绰����
				Intent intent = new Intent(Intent.ACTION_DIAL, Uri
						.parse("tel://" + phoneNumber));
				// ȥ������Щ���Դ�������Ϊ��Activity
				startActivity(intent);
			}
		});
		
	}
}