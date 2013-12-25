package com.zoe.weather;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.carlos.myslidingmenu.R;
import com.zoe.slidingmenu.view.SlidingMenu;
import com.zoe.slidingmenu.view.SlidingState;

public class MainActivity extends Activity {
	public static final String TAG = "MainActivity";
	private SlidingMenu coSlidingMenu;
	private String title[] = {"1111","2222"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.home);
		coSlidingMenu = (SlidingMenu) findViewById(R.id.slidingMenu);
		ViewGroup leftView = (ViewGroup) getLayoutInflater().inflate(R.layout.left_v, null);
		ViewGroup rightView = (ViewGroup) getLayoutInflater().inflate(R.layout.right_v, null);
		ViewGroup centerView = (ViewGroup) getLayoutInflater().inflate(R.layout.center_v, null);
		coSlidingMenu.setCenterView(centerView);
		int leftWidth = (int) getResources().getDimension(R.dimen.leftViewWidth);
		int rightWidth = (int) getResources().getDimension(R.dimen.rightViewWidth);
		coSlidingMenu.setLeftView(leftView, leftWidth);
		coSlidingMenu.setRightView(rightView, rightWidth);

		View ivRight = centerView.findViewById(R.id.ivRight);

		ivRight.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "点击了右边设置按钮");
				if (coSlidingMenu.getCurrentUIState() == SlidingState.SHOWRIGHT) {
					coSlidingMenu.showViewState(SlidingState.SHOWCENTER);
				} else {
					coSlidingMenu.showViewState(SlidingState.SHOWRIGHT);
				}
			}
		});
		View ivLeft = centerView.findViewById(R.id.ivLeft);

		ivLeft.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "点击了左边设置按钮");
				if (coSlidingMenu.getCurrentUIState() == SlidingState.SHOWLEFT) {
					coSlidingMenu.showViewState(SlidingState.SHOWCENTER);
				} else {
					coSlidingMenu.showViewState(SlidingState.SHOWLEFT);
				}
			}
		});

		// 设置左边的listview
		ListView lvLeft = (ListView) leftView.findViewById(R.id.lvLeft);

		lvLeft.setAdapter(new ArrayAdapter<String>(this, R.layout.item, R.id.tv_item, title));
		lvLeft.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

				Log.d(TAG, "点击了左边的" + arg2);

			}
		});

	}

}