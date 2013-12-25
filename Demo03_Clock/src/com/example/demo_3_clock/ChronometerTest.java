package com.example.demo_3_clock;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;

public class ChronometerTest extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//��ȡ��ʱ�����
		final Chronometer ch = (Chronometer)findViewById(R.id.test);
		//��ȡ����ʼ����ť
		Button start = (Button)findViewById(R.id.start);
		start.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				//���ÿ�ʼ��ʱʱ��
				ch.setBase(SystemClock.elapsedRealtime());
				//������ʱ��
				ch.start();				
			}
		});	
//		ch.setOnChronometerTickListener(new OnChronometerTickListener()
//		{
//			@Override
//			public void onChronometerTick(Chronometer ch)
//			{
//				//����ӿ�ʼ��ʱ�����ڳ�����20s��
//				if(SystemClock.elapsedRealtime() - ch.getBase()
//					 > 20 * 1000)
//				{
//					ch.stop();
//				}
//			}
//		});
		Button stop = (Button)findViewById(R.id.stop);
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ch.stop();
			}
		});
	}
}
