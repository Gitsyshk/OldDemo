package com.zoe.baidumap;

import android.app.Activity;
import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapView;

public class Main extends Activity{
	private MapView mapView;
	//���ص�ͼ������
	private BMapManager bMapManager;
	//�ٶȵ�ͼ��Key��������
	private final String keyString ="b60dada5d5e1b004e6995af64714e24f";
	//�Ŵ���С�Ŀؼ�
	private MapController mapController;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mapView = (MapView)findViewById(R.id.bmapView);
		bMapManager = new BMapManager(this);
	}
}
