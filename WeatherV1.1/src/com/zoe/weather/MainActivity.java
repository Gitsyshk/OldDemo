package com.zoe.weather;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zoe.slidingmenu.view.SlidingMenu;
import com.zoe.slidingmenu.view.SlidingState;

public class MainActivity extends Activity {

	private SlidingMenu slidingMenu;
	private SharedPreferences preferences;
	private SharedPreferences.Editor editor;
	private TextView dataText, cityName, temp, temp1;
	private String title[] = { "1111", "2222" };
	private MyApplication application;
	private Renew_2 renew_2;
	private Renew_1 renew_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.home);

		preferences = getSharedPreferences("zoe", MODE_PRIVATE);
		editor = preferences.edit();

		application = (MyApplication) getApplication();
		slidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
		ViewGroup leftView = (ViewGroup) getLayoutInflater().inflate(
				R.layout.left_v, null);
		ViewGroup rightView = (ViewGroup) getLayoutInflater().inflate(
				R.layout.right_v, null);
		ViewGroup centerView = (ViewGroup) getLayoutInflater().inflate(
				R.layout.center_v, null);
		dataText = (TextView) centerView.findViewById(R.id.dataView);
		cityName = (TextView) centerView.findViewById(R.id.cityView);
		temp = (TextView) centerView.findViewById(R.id.temperature);
		temp1 = (TextView) centerView.findViewById(R.id.temperatureText);
		slidingMenu.setCenterView(centerView);
		int leftWidth = (int) getResources()
				.getDimension(R.dimen.leftViewWidth);
		int rightWidth = (int) getResources().getDimension(
				R.dimen.rightViewWidth);
		slidingMenu.setLeftView(leftView, leftWidth);
		slidingMenu.setRightView(rightView, rightWidth);

		// 数据初始化
		String city = preferences.getString("city", null);
		application.city = city == null ? "北京" : city;
		String city_id = preferences.getString("city_id", null);
		application.city_id = city_id == null ? "101010100" : city_id;
		//
		dataText.setText(getDate());

		// 实现天气功能
		renew_2 = new Renew_2(application);
		renew_1 = new Renew_1(application);
		renew_1.start();
		renew_2.start();

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0x123) {
					cityName.setText(application.city);
					temp.setText(application.myData_1.weatherinfo.temp + "°");
					temp1.setText("温度:"
							+ (application.myData_2.weatherinfo.temp1 + "-" + application.myData_2.weatherinfo.temp2)
									.replace("℃", "") + "℃");
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
				Log.d("S", "点击了右边设置按钮");
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
				Log.d("S", "点击了左边设置按钮");
				if (slidingMenu.getCurrentUIState() == SlidingState.SHOWLEFT) {
					slidingMenu.showViewState(SlidingState.SHOWCENTER);
				} else {
					slidingMenu.showViewState(SlidingState.SHOWLEFT);
				}
			}
		});

		// 设置左边的listview
		ListView lvLeft = (ListView) leftView.findViewById(R.id.lvLeft);

		lvLeft.setAdapter(new ArrayAdapter<String>(this, R.layout.item,
				R.id.tv_item, title));
		lvLeft.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Log.d("S", "点击了左边的" + arg2);
			}
		});

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
}