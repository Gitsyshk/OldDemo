package com.zoe.baidumap;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;

public class Main extends Activity{
	private MapView mapView;
	//加载地图的引擎
	private BMapManager bMapManager;
	//百度地图的Key，需申请
	private final String keyString ="b60dada5d5e1b004e6995af64714e24f";
	//放大、缩小的控件
	private MapController mapController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mapView = (MapView)findViewById(R.id.bmapView);
		bMapManager = new BMapManager(this);
	}
}
