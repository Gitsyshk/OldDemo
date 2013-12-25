package com.zoe.game_fruitninja;

import android.graphics.Color;

import com.zoe.framework.Game;
import com.zoe.framework.Graphics;
import com.zoe.framework.Screen;

public class AboutScreen extends Screen {

	public AboutScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.zaizai,60,10);
		g.drawLine(60, 10, 420, 10, Color.BLACK);
		g.drawLine(60, 10, 60, 370, Color.BLACK);
		g.drawLine(60, 370, 420, 370, Color.BLACK);
		g.drawLine(420, 370, 420, 10, Color.BLACK);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
