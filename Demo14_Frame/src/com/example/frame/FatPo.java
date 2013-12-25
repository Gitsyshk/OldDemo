package com.example.frame;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.demo14_frame.R;

public class FatPo extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button play = (Button)findViewById(R.id.play);
		Button stop = (Button)findViewById(R.id.stop);
		ImageView imageView = (ImageView)findViewById(R.id.anim);
		final AnimationDrawable anim = (AnimationDrawable)imageView.getBackground();
		play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				anim.start();
				
			}
		});
		stop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				anim.stop();
				
			}
		});
		
	}
}
