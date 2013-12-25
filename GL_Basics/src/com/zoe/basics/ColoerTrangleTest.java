package com.zoe.basics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.zoe.framework.Game;
import com.zoe.framework.Screen;
import com.zoe.framework.impl.GLGame;
import com.zoe.framework.impl.GLGraphics;

public class ColoerTrangleTest extends GLGame {

	@Override
	public Screen getStartScreen() {

		return new ColoerTriangle(this);
	}

	class ColoerTriangle extends Screen {

		final int VERTEX_SIZE = (2 + 4) * 4;
		GLGraphics graphics;
		FloatBuffer buffer;

		public ColoerTriangle(Game game) {
			super(game);
			graphics = ((GLGame) (game)).getGLGraphics();
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3 * VERTEX_SIZE);
			
			byteBuffer.order(ByteOrder.nativeOrder());
			buffer = byteBuffer.asFloatBuffer();
			buffer.put(new float[] { 0.0f, 0.0f, 1, 0, 0, 1, 319.0f, 0.0f, 0,
					1, 0, 1, 160.0f, 479.0f, 0, 0, 1, 1, });
			buffer.flip();
		}

		@Override
		public void update(float deltaTime) {

		}

		@Override
		public void present(float deltaTime) {
			GL10 gl = graphics.getGL();
			gl.glViewport(0, 0, graphics.getWidth(), graphics.getHeight());
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			 gl.glOrthof(0, 320, 0, 480, 1, -1);

			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

			buffer.position(0);
			gl.glVertexPointer(2, GL10.GL_FLOAT, VERTEX_SIZE, buffer);
			buffer.position(2);
			gl.glColorPointer(4, GL10.GL_FLOAT, VERTEX_SIZE, buffer);
			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
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
}