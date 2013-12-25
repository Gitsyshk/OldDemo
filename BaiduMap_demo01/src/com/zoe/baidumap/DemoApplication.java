package com.zoe.baidumap;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.MKEvent;

public class DemoApplication extends Application {
	private static DemoApplication mInstance = null;
	public boolean m_bKeyRight = true;
	//��ͼ���������
	BMapManager mBMapManager = null;
	
	//���뵽��KEY
	public static final String strKey = "b60dada5d5e1b004e6995af64714e24f";

	@Override
	public void onCreate() {

		super.onCreate();
		mInstance = this;
		initEngineManager(this);
	}

	public void initEngineManager(Context context) {
		if (mBMapManager == null) {
			mBMapManager = new BMapManager(context);
		}

		if (!mBMapManager.init(strKey, new MyGeneralListener())) {
			Toast.makeText(
					DemoApplication.getInstance().getApplicationContext(),
					"BMapManager  ��ʼ������!", Toast.LENGTH_LONG).show();
		}
	}

	public static DemoApplication getInstance() {
		return mInstance;
	}

	static class MyGeneralListener implements MKGeneralListener {

		@Override
		public void onGetNetworkState(int iError) {
			if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
				Toast.makeText(
						DemoApplication.getInstance().getApplicationContext(),
						"���������������", Toast.LENGTH_LONG).show();
			} else if (iError == MKEvent.ERROR_NETWORK_DATA) {
				Toast.makeText(
						DemoApplication.getInstance().getApplicationContext(),
						"������ȷ�ļ���������", Toast.LENGTH_LONG).show();
			}
			// ...
		}

		@Override
		public void onGetPermissionState(int iError) {
			if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
				// ��ȨKey����
				Toast.makeText(
						DemoApplication.getInstance().getApplicationContext(),
						"���� DemoApplication.java�ļ�������ȷ����ȨKey��",
						Toast.LENGTH_LONG).show();
				DemoApplication.getInstance().m_bKeyRight = false;
			}
		}
	}
}
