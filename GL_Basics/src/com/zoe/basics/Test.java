package com.zoe.basics;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class Test extends Activity {
	GLSurfaceView glSurfaceView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		glSurfaceView = new GLSurfaceView(this);
		glSurfaceView.setRenderer(new SimpleRenderer());
		setContentView(glSurfaceView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		glSurfaceView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		glSurfaceView.onResume();
	}

	class SimpleRenderer implements Renderer {
		Random random = new Random();

		@Override
		public void onDrawFrame(GL10 gl) {
			gl.glClearColor(random.nextInt(), random.nextInt(),
					random.nextInt(), 1);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			Log.d("GLSurfaceView", "长:" + width + "\t宽:" + height);
		}

		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			Log.d("GLSurfaceView", "被创建了！");
		}

	}
}