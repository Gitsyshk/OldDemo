package com.zoe.basics;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import com.zoe.framework.Game;
import com.zoe.framework.Screen;
import com.zoe.framework.impl.GLGame;
import com.zoe.framework.impl.GLGraphics;

public class GLGameTest extends GLGame {

	@Override
	public Screen getStartScreen() {
		return new TestScreen(this);
	}
	class TestScreen extends Screen
	{
		GLGraphics glGraphics;
		Random random = new Random();

		public TestScreen(Game game) {
			super(game);
			glGraphics = ((GLGame)(game)).getGLGraphics();
		}

		@Override
		public void update(float deltaTime) {
						
		}

		@Override
		public void present(float deltaTime) {
			GL10 gl = glGraphics.getGL();
			gl.glClearColor(random.nextInt(), random.nextInt(), random.nextInt(), 1);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		}

		@Override
		public void pause() {
					
		}

		@Override
		public void resume() {
				
		}

		@Override
		public void dispose() {
						
		}		
	}
}
