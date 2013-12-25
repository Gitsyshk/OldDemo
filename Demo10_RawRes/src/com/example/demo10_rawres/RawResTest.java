package com.example.demo10_rawres;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RawResTest extends Activity {
	MediaPlayer mediaPlay1 = null;
	MediaPlayer mediaPlay2 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mediaPlay1 = MediaPlayer.create(this, R.raw.bom);
//		mediaPlay2 = MediaPlayer.create(this, R.raw.boom);
		AssetManager am = getAssets();
		try {
			AssetFileDescriptor adf = am.openFd("bom.mp3");
			mediaPlay2 = new MediaPlayer();
			mediaPlay2.setDataSource(adf.getFileDescriptor());
			mediaPlay2.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Button palyBom = (Button) findViewById(R.id.start1);
		palyBom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mediaPlay1.start();

			}
		});
		Button palyBoom = (Button) findViewById(R.id.start2);
		palyBoom.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mediaPlay2.start();

			}
		});
	}
}
