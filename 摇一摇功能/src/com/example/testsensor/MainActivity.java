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
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); // ��ȡ�������������
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE); // ��
		player = MediaPlayer.create(this, R.raw.shake);
		id_text.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				id_text.setText("ҡһҡ");
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (sensorManager != null) {// ע�������
			sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI);
			// ��һ��������Listener���ڶ������������ô��������ͣ�����������ֵ��ȡ��������Ϣ��Ƶ��
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
				Toast.makeText(MainActivity.this, "��⵽ҡ�Σ�ִ�в�����", Toast.LENGTH_SHORT).show();
				break;
			}
		}
	};

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		// �����������ȸı�ʱ�ص��÷�����Do nothing.
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		float x = values[0];
		float y = values[1];
		float z = values[2];

		Log.i("uri", "x�᷽����������ٶ�" + x + ";y�᷽����������ٶ�" + y + ";z�᷽����������ٶ�" + z);
		// һ����������������������ٶȴﵽ40�ʹﵽ��ҡ���ֻ���״̬��
		int medumValue = 18;
		if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
			id_text.setText("��˿��ʿ");
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
