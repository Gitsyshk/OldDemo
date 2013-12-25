package com.zoe.helloservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainHelloService extends Activity {

	//为日志工具设置标签
	String tag = "MusicService";
	
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //输出Toast消息和日志记录
		Toast.makeText(MainHelloService.this, "MainHelloService onCreate", Toast.LENGTH_SHORT).show();
		Log.i(tag, "MainHelloService onCreate");

        //定义组件对象
         b1= (Button)findViewById(R.id.button1);
         b2= (Button)findViewById(R.id.button2);
         b3= (Button)findViewById(R.id.button3);
         b4= (Button)findViewById(R.id.button4);

         //定义服务链接对象
         final ServiceConnection conn = new ServiceConnection(){

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				Toast.makeText(MainHelloService.this, "ServiceConnection onServiceConnected", Toast.LENGTH_SHORT).show();
				Log.i(tag, "ServiceConnection onServiceConnected");

			}

			@Override
			public void onServiceDisconnected(ComponentName name) {
				Toast.makeText(MainHelloService.this, "ServiceConnection onServiceDisconnected", Toast.LENGTH_SHORT).show();
				Log.i(tag, "ServiceConnection onServiceDisconnected");

			}};

		//定义点击监听器
        OnClickListener ocl= new OnClickListener(){

			@Override
			public void onClick(View v) {
				//显示指定intent所指的对象是个Service
				Intent intent = new Intent(MainHelloService.this,com.zoe.helloservice.MusicService.class);
				switch(v.getId()){
				case R.id.button1:
					//开始服务
					startService(intent);
					break;
				case R.id.button2:
					//停止服务
					stopService(intent);
					break;
				case R.id.button3:
					//绑定服务
					bindService(intent,conn,Context.BIND_AUTO_CREATE);
					break;
				case R.id.button4:
					//解除绑定
					unbindService(conn);
					break;
				}
			}
        };

        //绑定点击监听器
        b1.setOnClickListener(ocl);
        b2.setOnClickListener(ocl);
        b3.setOnClickListener(ocl);
        b4.setOnClickListener(ocl);    

    }

    @Override
    public void onDestroy(){
    	super.onDestroy();
		Toast.makeText(MainHelloService.this, "MainHelloService onDestroy", Toast.LENGTH_SHORT).show();
		Log.i(tag, "MainHelloService onDestroy");
    }
}