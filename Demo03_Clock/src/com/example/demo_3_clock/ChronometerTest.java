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
		//获取计时器组件
		final Chronometer ch = (Chronometer)findViewById(R.id.test);
		//获取“开始”按钮
		Button start = (Button)findViewById(R.id.start);
		start.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				//设置开始计时时间
				ch.setBase(SystemClock.elapsedRealtime());
				//启动计时器
				ch.start();				
			}
		});	
//		ch.setOnChronometerTickListener(new OnChronometerTickListener()
//		{
//			@Override
//			public void onChronometerTick(Chronometer ch)
//			{
//				//如果从开始计时到现在超过了20s。
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
