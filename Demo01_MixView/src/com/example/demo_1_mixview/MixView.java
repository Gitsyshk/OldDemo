package com.example.demo_1_mixview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MixView extends Activity {
	int[] images = new int[] { R.drawable.image1, R.drawable.image2,
			R.drawable.image3, R.drawable.image4, R.drawable.image5,
			R.drawable.image6, R.drawable.image7, };
	int currentImg = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		LinearLayout main = (LinearLayout) findViewById(R.id.root);
		final ImageView image = new ImageView(this);
		main.addView(image);
		image.setImageResource(images[0]);
		image.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (currentImg >= 7) {
					currentImg = -1;
				}
				image.setImageResource(images[++currentImg]);
			}
		});
	}
}
