package com.zoe.weather;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zoe.data.AutoCompleteAdater;
import com.zoe.data.DataBaseHelper;
import com.zoe.slidingmenu.view.SlidingMenu;
import com.zoe.slidingmenu.view.SlidingState;

public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager sensorManager;
	private MediaPlayer player;

	private SlidingMenu slidingMenu;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private TextView dataText, cityName, temp, temp1, weatherText, windText,
			humidityText, uvbText, tourText;

	private MyApplication application;
	private Refresh refresh;
	private DataBaseHelper myDbHelper;
	private AutoCompleteTextView autoCompleteTextView;
	private Button searchButton;

	private Handler handler;

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);

		preferences = getSharedPreferences("zoe", MODE_PRIVATE);
		editor = preferences.edit();

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); // 获取传感器管理服务
		player = MediaPlayer.create(this, R.raw.shake);

		// 数据库初始化
		myDbHelper = new DataBaseHelper(this);
		try {
			myDbHelper.createDataBase();
			myDbHelper.openDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}

		application = (MyApplication) getApplication();
		slidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
		ViewGroup leftView = (ViewGroup) getLayoutInflater().inflate(
				R.layout.left_v, null);
		ViewGroup rightView = (ViewGroup) getLayoutInflater().inflate(
				R.layout.right_v, null);
		ViewGroup centerView = (ViewGroup) getLayoutInflater().inflate(
				R.layout.center_v, null);
		autoCompleteTextView = (AutoCompleteTextView) rightView
				.findViewById(R.id.autoCompleteTextView);
		dataText = (TextView) centerView.findViewById(R.id.dataView);
		cityName = (TextView) centerView.findViewById(R.id.cityView);
		temp = (TextView) centerView.findViewById(R.id.temperature);
		temp1 = (TextView) centerView.findViewById(R.id.temperatureText);
		weatherText = (TextView) centerView.findViewById(R.id.weatherText);
		windText = (TextView) centerView.findViewById(R.id.windText);
		humidityText = (TextView) centerView.findViewById(R.id.humidityText);
		uvbText = (TextView) centerView.findViewById(R.id.uvbText);
		tourText = (TextView) centerView.findViewById(R.id.tourText);

		slidingMenu.setCenterView(centerView);
		int leftWidth = (int) getResources()
				.getDimension(R.dimen.leftViewWidth);
		int rightWidth = (int) getResources().getDimension(
				R.dimen.rightViewWidth);
		slidingMenu.setLeftView(leftView, leftWidth);
		slidingMenu.setRightView(rightView, rightWidth);
		searchButton = (Button) rightView.findViewById(R.id.search);

		// 初始化自动补全框
		AutoCompleteAdater cursorAdapter = new AutoCompleteAdater(this,
				android.R.layout.simple_dropdown_item_1line, null, "name",
				android.R.id.text1, myDbHelper);
		autoCompleteTextView.setAdapter(cursorAdapter);

		// 数据初始化
		String city = preferences.getString("city", null);
		application.city = city == null ? "北京" : city;
		String city_id = preferences.getString("city_id", null);
		application.city_id = city_id == null ? "101010100" : city_id;
		//
		dataText.setText(getDate());

		// 实现天气功能
		refresh = new Refresh(application);
		refresh.start();

		handler = new Handler() {
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					reNew();
				}
				if (msg.what == 0x111) {
					Toast.makeText(MainActivity.this, "检测到摇晃，执行操作！",
							Toast.LENGTH_SHORT).show();
					refresh.setFlag();
					reNew();
				}
			}
		};
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				Message msg = new Message();
				msg.what = 0x123;
				handler.sendMessage(msg);
			}
		}, 0, 1000);

		View ivRight = centerView.findViewById(R.id.settingView);

		ivRight.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (slidingMenu.getCurrentUIState() == SlidingState.SHOWRIGHT) {
					slidingMenu.showViewState(SlidingState.SHOWCENTER);
				} else {
					slidingMenu.showViewState(SlidingState.SHOWRIGHT);
				}
			}
		});
		View ivLeft = centerView.findViewById(R.id.menuView);

		ivLeft.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (slidingMenu.getCurrentUIState() == SlidingState.SHOWLEFT) {
					slidingMenu.showViewState(SlidingState.SHOWCENTER);
				} else {
					slidingMenu.showViewState(SlidingState.SHOWLEFT);
				}
			}
		});

		// 设置左边的listview
		ListView lvLeft = (ListView) leftView.findViewById(R.id.lvLeft);

		// lvLeft.setAdapter(new ArrayAdapter<String>(this, R.layout.item,
		// R.id.tv_item, title));
		// lvLeft.setOnItemClickListener(new OnItemClickListener() {
		//
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
		// long arg3) {
		//
		// }
		// });
		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String city = autoCompleteTextView.getText().toString();
				if (!city.equals("")) {
					Cursor cursor = myDbHelper.getReadableDatabase().rawQuery(
							"select id from user where " + "name = \"" + city
									+ "\"", null);
					
					while (cursor.moveToNext()) {
						String id = cursor.getString(cursor
								.getColumnIndex("id"));
						Log.d("msg", id);
						application.city_id = id;
						application.city = city;
						Message msg = new Message();
						msg.what = 0x111;
						handler.sendMessage(msg);
						slidingMenu.showViewState(SlidingState.SHOWCENTER);
					}
				}
			}

		});

	}

	private void reNew() {
		cityName.setText(application.city);
		editor.putString("city", application.city);
		editor.commit();
		if (application.myData_1.weatherinfo.temp != null)
			temp.setText(application.myData_1.weatherinfo.temp + "°");

		if (application.myData_2.weatherinfo.temp1 != null
				&& application.myData_2.weatherinfo.temp2 != null)
			temp1.setText("温度:"
					+ (application.myData_2.weatherinfo.temp1 + "-" + application.myData_2.weatherinfo.temp2)
							.replace("℃", "") + "℃");
		if (application.myData_2.weatherinfo.weather != null)
			weatherText.setText(application.myData_2.weatherinfo.weather);
		if (application.myData_1.weatherinfo.WD != null
				&& application.myData_1.weatherinfo.WS != null)
			windText.setText(application.myData_1.weatherinfo.WD + ":"
					+ application.myData_1.weatherinfo.WS);
		if (application.myData_1.weatherinfo.SD != null)
			humidityText.setText("湿度:" + application.myData_1.weatherinfo.SD);
		if (application.myData_3.weatherinfo.index_uv != null)
			uvbText.setText("紫外线:" + application.myData_3.weatherinfo.index_uv);
		if (application.myData_3.weatherinfo.index_tr != null)
			tourText.setText("旅游指数:"
					+ application.myData_3.weatherinfo.index_tr);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (sensorManager != null) {// 注册监听器
			sensorManager.registerListener(this,
					sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
					SensorManager.SENSOR_DELAY_UI);
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

	private String getDate() {
		final Calendar c = Calendar.getInstance();
		String mMonth, mDay, mWay;
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
		// mYear = String.valueOf(c.get(Calendar.YEAR)); // 获取当前年份
		mMonth = String.valueOf(c.get(Calendar.MONTH) + 1);// 获取当前月份
		mDay = String.valueOf(c.get(Calendar.DAY_OF_MONTH));// 获取当前月份的日期号码
		mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		if ("1".equals(mWay)) {
			mWay = "周日";
		} else if ("2".equals(mWay)) {
			mWay = "周一";
		} else if ("3".equals(mWay)) {
			mWay = "周二";
		} else if ("4".equals(mWay)) {
			mWay = "周三";
		} else if ("5".equals(mWay)) {
			mWay = "周四";
		} else if ("6".equals(mWay)) {
			mWay = "周五";
		} else if ("7".equals(mWay)) {
			mWay = "周六";
		}
		return mMonth + "." + mDay + "/" + mWay;
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float[] values = event.values;
		float x = values[0];
		float y = values[1];
		float z = values[2];

		// 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
		int medumValue = 18;
		if (Math.abs(x) > medumValue || Math.abs(y) > medumValue
				|| Math.abs(z) > medumValue) {
			player.start();
			player.setOnCompletionListener(new OnCompletionListener() {

				@Override
				public void onCompletion(MediaPlayer mp) {
					// TODO Auto-generated method stub
					player.pause();
					player.seekTo(0);
				}
			});
			Message msg = new Message();
			msg.what = 0x111;
			handler.sendMessage(msg);
		}
	}
}