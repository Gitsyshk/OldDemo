package com.zoe.helloservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {

	String tag = "MusicService";
	MediaPlayer mPlayer;

	@Override
	public IBinder onBind(Intent arg0) {
		Toast.makeText(this, "MusicService onBind()", Toast.LENGTH_SHORT)
				.show();
		Log.i(tag, "MusicService onBind()");
		mPlayer.start();
		return null;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(this, "MusicService onUnbind()", Toast.LENGTH_SHORT).show();
		Log.i(tag,"MusicService onUnbind()");
		mPlayer.stop();
		return false;
	}

	@Override
	public void onCreate() {
		Toast.makeText(this, "MusicService onCreate()", Toast.LENGTH_SHORT)
				.show();
		Log.i(tag, "MusicService onCreate()");
		mPlayer = mPlayer.create(getApplicationContext(), R.raw.menu);
		mPlayer.setLooping(true);
		Log.i(tag, "MusicService onCreate()");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Toast.makeText(this, "MusicService onStart", Toast.LENGTH_SHORT).show();
		Log.i(tag, "MusicService onStart()");
		mPlayer.start();
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "MusicService onDestroy()", Toast.LENGTH_SHORT)
				.show();
		mPlayer.stop();
		Log.i(tag, "MusicService onDestroy()");
	}
}
