package com.zoe.androidgame;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class SoundPoolTest extends Activity implements OnTouchListener {
	SoundPool soudPool; // SoundPool µ¿˝øÿ÷∆“Ù–ß
	int explosionId = -1;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setOnTouchListener(this);
		setContentView(textView);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		soudPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
		try {
			AssetManager assetManager = getAssets();
			AssetFileDescriptor descriptor = assetManager
					.openFd("splatter.ogg");
			explosionId = soudPool.load(descriptor, 1);
		} catch (IOException e) {
			textView.setText("wrong" + e.getMessage());
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_UP) {
			soudPool.play(explosionId, 1, 1, 0, 0, 1);
		}
		return true;
	}
	@Override
	protected void onPause() {
		super.onPause();
		soudPool.release();
	}
}
