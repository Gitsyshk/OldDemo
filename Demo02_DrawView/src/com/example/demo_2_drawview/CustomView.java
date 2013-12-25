package com.example.demo_2_drawview;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class CustomView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        LinearLayout root = (LinearLayout)findViewById(R.id.root);
        final DrawView draw = new DrawView(this);
        draw.setMinimumHeight(300);
        draw.setMinimumWidth(500);
        draw.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				draw.currentX = event.getX();
				draw.currentY = event.getY();
				draw.invalidate();
				return true;
			}
		});
        root.addView(draw);
    }    
}
