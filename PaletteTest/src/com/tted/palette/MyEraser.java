package com.tted.palette;

import android.graphics.*;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;

public class MyEraser {
	Path path;
	int size;
	
	MyEraser() {
		path=new Path();
		size=1;
	}

	MyEraser(float x,float y,int size, int color) {
		path=new Path();
		this.size=size;
		path.moveTo(x, y);
		path.lineTo(x, y);
	}
	
	public void draw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(size);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        canvas.drawPath(path, paint);
	}
	
	public void move(float mx,float my){
		path.lineTo(mx, my);
	}
}