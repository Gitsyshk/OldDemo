package com.zoe.game_fruitninja;

import com.zoe.framework.Game;
import com.zoe.framework.Graphics;
import com.zoe.framework.Graphics.PixmapFormat;
import com.zoe.framework.Screen;

public class LoadingScreen extends Screen {

	public LoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.background = g.newPixmap("background.jpg", PixmapFormat.RGB565);
		Assets.logo = g.newPixmap("logo.png", PixmapFormat.ARGB4444);
		Assets.home_make =  g.newPixmap("home-mask.png", PixmapFormat.ARGB4444);
		Assets.$new = g.newPixmap("new.png", PixmapFormat.ARGB4444);
		Assets.developing = g
				.newPixmap("developing.png", PixmapFormat.ARGB4444);
		Assets.dojo = g.newPixmap("dojo.png", PixmapFormat.ARGB4444);
		Assets.gameover = g.newPixmap("game-over.png", PixmapFormat.ARGB4444);
		Assets.quit = g.newPixmap("quit.png", PixmapFormat.ARGB4444);
		Assets.ninja = g.newPixmap("ninja.png", PixmapFormat.ARGB4444);
		Assets.lose = g.newPixmap("lose.png", PixmapFormat.ARGB4444);
		Assets.new_game = g.newPixmap("new-game.png", PixmapFormat.ARGB4444);
		Assets.shadow = g.newPixmap("shadow.png", PixmapFormat.ARGB4444);

		Assets.x = g.newPixmap("x.png", PixmapFormat.ARGB4444);
		Assets.xx = g.newPixmap("xx.png", PixmapFormat.ARGB4444);
		Assets.xxx = g.newPixmap("xxx.png", PixmapFormat.ARGB4444);
		Assets.xf = g.newPixmap("xf.png", PixmapFormat.ARGB4444);
		Assets.xxf = g.newPixmap("xxf.png", PixmapFormat.ARGB4444);
		Assets.xxxf = g.newPixmap("xxxf.png", PixmapFormat.ARGB4444);

		Assets.number_0 = g.newPixmap("number_0.png",
				PixmapFormat.ARGB4444);
		Assets.number_1 = g.newPixmap("number_1.png",
				PixmapFormat.ARGB4444);
		Assets.number_2 = g.newPixmap("number_2.png",
				PixmapFormat.ARGB4444);
		Assets.number_3 = g.newPixmap("number_3.png",
				PixmapFormat.ARGB4444);
		Assets.number_4 = g.newPixmap("number_4.png",
				PixmapFormat.ARGB4444);
		Assets.number_5 = g.newPixmap("number_5.png",
				PixmapFormat.ARGB4444);
		Assets.number_6 = g.newPixmap("number_6.png",
				PixmapFormat.ARGB4444);
		Assets.number_7 = g.newPixmap("number_7.png",
				PixmapFormat.ARGB4444);
		Assets.number_8 = g.newPixmap("number_8.png",
				PixmapFormat.ARGB4444);
		Assets.number_9 = g.newPixmap("number_9.png",
				PixmapFormat.ARGB4444);

		Assets.boom = g.newPixmap("boom.png", PixmapFormat.ARGB4444);
		Assets.apple = g.newPixmap("apple.png", PixmapFormat.ARGB4444);
		Assets.apple_1 = g
				.newPixmap("apple-1.png", PixmapFormat.ARGB4444);
		Assets.apple_2 = g
				.newPixmap("apple-2.png", PixmapFormat.ARGB4444);
		Assets.banana = g.newPixmap("banana.png", PixmapFormat.ARGB4444);
		Assets.banana_1 = g.newPixmap("banana-1.png",
				PixmapFormat.ARGB4444);
		Assets.banana_2 = g.newPixmap("banana-2.png",
				PixmapFormat.ARGB4444);
		Assets.basaha = g.newPixmap("basaha.png", PixmapFormat.ARGB4444);
		Assets.basaha_1 = g.newPixmap("basaha-1.png",
				PixmapFormat.ARGB4444);
		Assets.basaha_2 = g.newPixmap("basaha-2.png",
				PixmapFormat.ARGB4444);
		Assets.peach = g.newPixmap("peach.png", PixmapFormat.ARGB4444);
		Assets.peach_1 = g
				.newPixmap("peach-1.png", PixmapFormat.ARGB4444);
		Assets.peach_2 = g
				.newPixmap("peach-2.png", PixmapFormat.ARGB4444);
		Assets.sandia = g.newPixmap("sandia.png", PixmapFormat.ARGB4444);
		Assets.sandia_1 = g.newPixmap("sandia-1.png",
				PixmapFormat.ARGB4444);
		Assets.sandia_2 = g.newPixmap("sandia-2.png",
				PixmapFormat.ARGB4444);
		Assets.zaizai = g.newPixmap("zaizai.png",
				PixmapFormat.ARGB4444);
		Assets.soundBoom = game.getAudio().newSound("boom.ogg");
		Assets.menu = game.getAudio().newMusic("menu.ogg");
		Assets.over = game.getAudio().newSound("over.ogg");
		Assets.splatter = game.getAudio().newSound("splatter.ogg");
		Assets.start = game.getAudio().newSound("start.ogg");
		Assets.soundThrow = game.getAudio().newSound("throw.ogg");
		Settings.load(game.getFileIO());
        game.setScreen(new MainMenuScreen(game));
		
	}

	@Override
	public void present(float deltaTime) {
		// TODO Auto-generated method stub

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
