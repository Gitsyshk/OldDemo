package com.zoe.androidgame;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.widget.TextView;

public class AccelerometerTest extends Activity implements SensorEventListener {
	TextView textView;
	StringBuilder builder = new StringBuilder();
	@Override
	public void onCreate(Bundle saveInstanceState)
	{	
		super.onCreate(saveInstanceState);
		textView = new TextView(this);
		setContentView(textView);
		SensorManager manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0)
		{
			textView.setText("没有重力感应或者未开启");
		}
		else
		{
			Sensor accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			if(!manager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_GAME))
			{
				textView.setText("注册失败");
			}
		}
	}
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		//什么都不写
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		builder.setLength(0);
		builder.append("X:"+(int)(event.values[0]*90/9.62)+"°"+"\nY:"+(int)(event.values[1]*90/9.62)+"°"+"\nZ:"+(int)(event.values[2]*90/9.62)+"°");
		textView.setText(builder.toString());
	}

}
