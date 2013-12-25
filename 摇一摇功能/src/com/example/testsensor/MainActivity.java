package com.example.testsensor;

import java.util.Random;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {

	Random r = new Random();

	private Vibrator vibrator;

	private static final int SENSOR_SHAKE = 10;

	private SensorManager sensorManager;

	private TextView id_text;

	private MediaPlayer player;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		id_text = (TextView) findViewById(R.id.id_text);
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); // 获取传感器管理服务
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE); // 震动
		player = MediaPlayer.create(this, R.raw.shake);
		id_text.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				id_text.setText("摇一摇");
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (sensorManager != null) {// 注册监听器
			sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
			// 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
		}
	}

	@Override
	protected void onStop() {
		sensorManager.unregisterListener(this);
		super.onStop();
		player.stop();
	}

	@Override
	protected void onPause() {
		sensorManager.unregisterListener(this);
		super.onPause();
	}

	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case SENSOR_SHAKE:
				Toast.makeText(MainActivity.this, "检测到摇晃，执行操作！", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		// 当传感器精度改变时回调该方法，Do nothing.
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		float x = values[0];
		float y = values[1];
		float z = values[2];

		Log.i("uri", "x轴方向的重力加速度" + x + ";y轴方向的重力加速度" + y + ";z轴方向的重力加速度" + z);
		// 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
		int medumValue = 18;
		if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
			id_text.setText("屌丝男士");
			try {
				player.start();
				player.setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						player.pause();
						player.seekTo(0);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
			vibrator.vibrate(500);
			Message msg = new Message();
			msg.what = SENSOR_SHAKE;
			handler.sendMessage(msg);
		}
	}
}
