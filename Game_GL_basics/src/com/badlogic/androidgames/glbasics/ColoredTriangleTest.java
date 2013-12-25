package com.badlogic.androidgames.glbasics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.badlogic.androidgames.framework.Game;
import com.badlogic.androidgames.framework.Screen;
import com.badlogic.androidgames.framework.impl.GLGame;
import com.badlogic.androidgames.framework.impl.GLGraphics;

public class ColoredTriangleTest extends GLGame {
	@Override
	public Screen getStartScreen() {
		return new ColoredTriangleScreen(this);
	}

	class ColoredTriangleScreen extends Screen {
		final int VERTEX_SIZE = (2 + 4) * 4;//x,y+RGBA位置与颜色信息
		GLGraphics glGraphics;
		FloatBuffer vertices;

		public ColoredTriangleScreen(Game game) {
			super(game);
			glGraphics = ((GLGame) game).getGLGraphics();

			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(3 * VERTEX_SIZE);
			byteBuffer.order(ByteOrder.nativeOrder());
			vertices = byteBuffer.asFloatBuffer();//以上三行构造一个直接NIO缓冲区
			vertices.put(new float[] { 
										0.0f,   0.0f, 0, 1, 0, 1, 
										319.0f, 0.0f, 1, 0, 0, 1, 
										160.0f, 479.0f, 0, 0, 1, 1 });//会给复制到缓冲区的最后一个数组的数据添加附加其他数据
			vertices.flip();//将位置值与界限值交换
		}

		@Override
		public void present(float deltaTime) {
			GL10 gl = glGraphics.getGL();
			gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();
			gl.glOrthof(0, 320, 0, 480, 1, -1);

			gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//告诉OpenGL ES 这些需要绘制的顶点都有一个位置
			gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

			vertices.position(0);
			gl.glVertexPointer(2, GL10.GL_FLOAT, VERTEX_SIZE, vertices);
			/*
			 * 第一个参数告诉OpenGL ES每个顶点位置都是x,y坐标组成的
			 * 第二个参数告诉OpenGL ES坐标所使用的数据类型
			 * 第三个参数说明顶点位置值之间的字节距离
			 * 最后的参数是FloatBuffer
			 */
																				
			vertices.position(2);
			gl.glColorPointer(4, GL10.GL_FLOAT, VERTEX_SIZE, vertices);
			/*
			 * 用来说明顶点的颜色
			 */

			gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
			/*
			 * 第一个参数说明了绘制的物体类型
			 * 第二个参数是与顶点指针指向的第一个顶点相关的偏移量
			 * 最后一个参数OpenGL渲染时使用的顶点数
			 */
		}

		@Override
		public void update(float deltaTime) {
			game.getInput().getTouchEvents();
			game.getInput().getKeyEvents();
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
