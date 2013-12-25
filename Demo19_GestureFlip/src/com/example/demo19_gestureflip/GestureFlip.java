package com.example.demo19_gestureflip;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;


public class GestureFlip extends Activity implements OnGestureListener {
	
	ViewFlipper flipper;
	GestureDetector detector;
	Animation[] animations = new Animation[4];
	final int FILP_DISTANCE = 50;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		detector = new GestureDetector(this);
		flipper = (ViewFlipper)findViewById(R.id.flipper);
		flipper.addView(addImageView(R.drawable.test0));
		flipper.addView(addImageView(R.drawable.test1));
		flipper.addView(addImageView(R.drawable.test2));
		flipper.addView(addImageView(R.drawable.test3));
		flipper.addView(addImageView(R.drawable.test4));
		flipper.addView(addImageView(R.drawable.test5));
		animations[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
		animations[1] = AnimationUtils.loadAnimation(this, R.anim.left_out);
		animations[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
		animations[3] = AnimationUtils.loadAnimation(this, R.anim.right_out);
		
	}

	private View addImageView(int resId) {
	
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(resId);
		imageView.setScaleType(ImageView.ScaleType.CENTER);
		return imageView;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if(e1.getX() - e2.getX() > FILP_DISTANCE)
		{
			flipper.setInAnimation(animations[0]);
			flipper.setOutAnimation(animations[1]);
			flipper.showPrevious();
			return true;
		}
		else if(e2.getX() - e1.getX() >FILP_DISTANCE)
		{
			flipper.setInAnimation(animations[2]);
			flipper.setOutAnimation(animations[3]);
			flipper.showPrevious();
			return true;
		}
		return false;
	}

	public boolean onTouchEvent(MotionEvent event)
	{
		return detector.onTouchEvent(event);
	}
	@Override
	public void onLongPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

}
