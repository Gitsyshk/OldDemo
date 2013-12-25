package com.zoe.androidgame;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class MediaPlayerTest extends Activity {
	MediaPlayer mediaPlayer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		setContentView(textView);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		mediaPlayer = new MediaPlayer();
		try {
			AssetManager asserManager = getAssets();
			AssetFileDescriptor descriptor = asserManager.openFd("music.ogg");
			mediaPlayer.setDataSource(descriptor.getFileDescriptor(),
					descriptor.getStartOffset(), descriptor.getLength());
			mediaPlayer.prepare();
			mediaPlayer.setLooping(true);
		} catch (IOException e) {
			textView.setText("wrong:" + e.getMessage());
			mediaPlayer = null;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mediaPlayer != null) {
			mediaPlayer.start();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mediaPlayer != null) {
			if (isFinishing()) {
				mediaPlayer.stop();
				mediaPlayer.release();
			}
		}
	}
}
