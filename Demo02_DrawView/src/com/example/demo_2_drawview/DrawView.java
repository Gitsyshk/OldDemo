package com.example.demo_2_drawview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
	public float currentX = 40;
	public float currentY = 50;

	public DrawView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@SuppressLint("DrawAllocation")
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Paint p = new Paint();
		p.setColor(Color.BLUE);
		canvas.drawCircle(currentX, currentY, 15, p);
	}
}
