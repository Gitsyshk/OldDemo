package com.zoe.basics;

import java.util.Random;

public class Bob {
	static final Random rand = new Random();
	public float x, y;
	float dirX, dirY;
	public float angle;

	public Bob() {
		angle = rand.nextFloat()*360;
		x = rand.nextFloat() * 320;
		y = rand.nextFloat() * 480;
		dirX = 50;
		dirY = 50;
	}

	public void update(float deltaTiime) {
		x = x + dirX * deltaTiime;
		y = y + dirY * deltaTiime;
		angle += 1.0;
		if (x < 0) {
			dirX = -dirX;
			x = 0;
		}
		if (x > 320) {
			dirX = -dirX;
			x = 320;
		}
		if (y < 0) {
			dirY = -dirY;
			y = 0;
		}
		if (y > 480) {
			dirY = -dirY;
			y = 480;
		}
	}
}