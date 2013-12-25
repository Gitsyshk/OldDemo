package com.zoe.game_fruitninja;

import java.util.List;

import com.zoe.framework.Game;
import com.zoe.framework.Graphics;
import com.zoe.framework.Input.TouchEvent;
import com.zoe.framework.Screen;

public class GameScreen extends Screen {

	FruitGame fruitGame;

	public GameScreen(Game game) {
		super(game);
		fruitGame = new FruitGame();
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		fruitGame.update(deltaTime);
		for (int i = 0; i < touchEvents.size(); i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				for (int j = 0; j < fruitGame.fruitList.size(); j++)
					if (fruitGame.fruitList.get(j).flag == true) {
						if (event.x > fruitGame.fruitList.get(j).x
								&& event.x < fruitGame.fruitList.get(j).x
										+ fruitGame.fruitList.get(j).width
								&& event.y > fruitGame.fruitList.get(j).y
								&& event.y < fruitGame.fruitList.get(j).y
										+ fruitGame.fruitList.get(j).height) {
							
							Assets.splatter.play(1);
							fruitGame.fruitList.get(j).flag = false;
						}
					}
			}
		}
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawPixmap(Assets.background, 0, 0);
		for (int i = 0; i < fruitGame.fruitList.size(); i++) {
			if (fruitGame.fruitList.get(i).flag == true)
				drawFruit(fruitGame.fruitList.get(i), g);
			else {
				drawFruit_s(fruitGame.fruitList.get(i), g);
			}
		}
	}
	private void drawFruit_s(Fruit fruit,Graphics g)
	{	switch (fruit.type)
		{
		case 0:g.drawPixmap(Assets.apple_1,
				(int) fruit.x - 9,
				(int) fruit.y);
		g.drawPixmap(Assets.apple_2,
				(int) fruit.x + 8,
				(int) fruit.y);
		break;
		case 1:g.drawPixmap(Assets.banana_1,
				(int) fruit.x -9,
				(int) fruit.y);
		g.drawPixmap(Assets.banana_2,
				(int) fruit.x - 20,
				(int) fruit.y);
		break;
		case 2:g.drawPixmap(Assets.basaha_1,
				(int) fruit.x - 9,
				(int) fruit.y);
		g.drawPixmap(Assets.basaha_2,
				(int) fruit.x + 8,
				(int) fruit.y);
		break;
		case 3:g.drawPixmap(Assets.sandia_1,
				(int) fruit.x - 9,
				(int) fruit.y);
		g.drawPixmap(Assets.sandia_2,
				(int) fruit.x + 8,
				(int) fruit.y);
		break;
		case 4:g.drawPixmap(Assets.peach_1,
				(int) fruit.x - 9,
				(int) fruit.y);
		g.drawPixmap(Assets.peach_2,
				(int) fruit.x + 8,
				(int) fruit.y);
		break;
		case 5:g.drawPixmap(Assets.boom,
				(int) fruit.x - 9,
				(int) fruit.y);
		g.drawPixmap(Assets.boom,
				(int) fruit.x + 8,
				(int) fruit.y);
		break;
		}
		
	}
	private void drawFruit(Fruit fruit, Graphics g) {
		switch (fruit.type) {
		case 0:
			g.drawPixmap(Assets.apple, (int) fruit.x, (int) fruit.y);
			break;
		case 1:
			g.drawPixmap(Assets.banana, (int) fruit.x, (int) fruit.y);
			break;
		case 2:
			g.drawPixmap(Assets.basaha, (int) fruit.x, (int) fruit.y);
			break;
		case 3:
			g.drawPixmap(Assets.sandia, (int) fruit.x, (int) fruit.y);
			break;
		case 4:
			g.drawPixmap(Assets.peach, (int) fruit.x, (int) fruit.y);
			break;
		case 5:
			g.drawPixmap(Assets.boom, (int) fruit.x, (int) fruit.y);
			break;
		}
		return;
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
