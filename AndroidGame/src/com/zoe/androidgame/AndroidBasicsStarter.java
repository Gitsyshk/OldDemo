package com.zoe.androidgame;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AndroidBasicsStarter extends ListActivity {
	
	String tests[] = { "LifeCycleTest", "SingleTouchTest", "MultiTouchTest",
            "KeyTest", "AccelerometerTest", "AssetsTest",
            "ExternalStorageTest", "SoundPoolTest", "MediaPlayerTest",
            "FullScreenTest", "WakeLockTest", "RenderViewTest", "ShapeTest", "BitmapTest",
            "FontTest", "SurfaceViewTest" };
	String name[] = { "Activity生命周期", "单点触碰处理", "多点触碰处理",
            "按键处理", "加速计处理", "AssetsTest",
            "ExternalStorageTest", "SoundPoolTest", "MediaPlayerTest",
            "FullScreenTest", "WakeLockTest", "RenderViewTest", "ShapeTest", "BitmapTest",
            "FontTest", "SurfaceViewTest" };
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
	        setListAdapter(new ArrayAdapter<String>(this,
	                android.R.layout.simple_list_item_1, name));
	}

	@Override
	protected void onListItemClick(ListView list, View view,int position,long id)
	{
		super.onListItemClick(list, view, position, id);
		String testName = tests[position];
		try {
			Class clazz = Class.forName("com.zoe.androidgame."+testName);
			startActivity(new Intent(this,clazz));
		} catch (ClassNotFoundException e) {
			 e.printStackTrace();
		}
	}
}
