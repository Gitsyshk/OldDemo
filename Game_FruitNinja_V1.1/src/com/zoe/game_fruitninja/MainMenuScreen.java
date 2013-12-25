package com.zoe.game_fruitninja;

import java.util.List;

import com.zoe.framework.Game;
import com.zoe.framework.Graphics;
import com.zoe.framework.Input.TouchEvent;
import com.zoe.framework.Screen;

public class MainMenuScreen extends Screen {
	public MainMenuScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		Assets.menu.setLooping(true);
		Assets.menu.play();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);

			if (event.type == TouchEvent.TOUCH_DOWN) {

				if (inBounds(event, 85, 330, 147, 389)) {
					game.setScreen(new AboutScreen(game));

					Assets.splatter.play(1);
					return;
				}
				if (inBounds(event, 300, 300, 398, 385)) {
					Assets.splatter.play(1);
					game.setScreen(new GameScreen(game));
					return;
				}
				if (inBounds(event, 517, 360, 583, 428)) {
					// game.setScreen(new HelpScreen(game));
					// if (Settings.soundEnabled)
					Assets.splatter.play(1);
					return;
				}
			}
		}
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	@Override
	public void present(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawPixmap(Assets.background, 0, 0);
		g.drawPixmap(Assets.home_make, 0, 0);
		g.drawPixmap(Assets.logo, 5, 5);
		g.drawPixmap(Assets.quit, 480, 320);
		g.drawPixmap(Assets.boom, 517, 360);
		g.drawPixmap(Assets.new_game, 250, 250);
		g.drawPixmap(Assets.sandia, 300, 300);
		g.drawPixmap(Assets.dojo, 30, 280);
		g.drawPixmap(Assets.peach, 85, 330);

	}

	@Override
	public void pause() {
		Settings.save(game.getFileIO());
		if (Assets.menu.isPlaying()) {
			Assets.menu.pause();
		}
	}

	@Override
	public void resume() {
		Assets.menu.play();
	}

	@Override
	public void dispose() {

	}
}
