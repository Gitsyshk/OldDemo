package com.zoe.framework.impl;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.zoe.framework.Audio;
import com.zoe.framework.FileIO;
import com.zoe.framework.Game;
import com.zoe.framework.Graphics;
import com.zoe.framework.Input;
import com.zoe.framework.Screen;

public abstract class GLGame extends Activity implements Game, Renderer {
    /*
     * ��һ��ö�ټ�¼GLGameʵ����ǰ��״̬
     * Initialized	��ʼ��
     * Running	������
     * Paused	��ͣ
     * Finished	���
     * Idle		����
     */
	enum GLGameState {
        Initialized,
        Running,
        Paused,
        Finished,
        Idle
    }
    /*
     * ��д��Ϸ����Ҫ��һЩʵ��
     * ��¼״̬��state
     * ����ͬ��UI�̺߳���Ⱦ�̵߳Ķ���stateChanged
     * startTime��¼ʱ���
     */
    GLSurfaceView glView;    
    GLGraphics glGraphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    GLGameState state = GLGameState.Initialized;
    Object stateChanged = new Object();
    long startTime = System.nanoTime();
    WakeLock wakeLock;
    /*
     * @see android.app.Activity#onCreate(android.os.Bundle)
     * ��һЩ����ĳ�ʼ����
     * ���û��Ϊȫ����
     * ʵ����GLSurfaceView��������Ϊ������ͼ��
     */
    @Override 
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        glView = new GLSurfaceView(this);
        glView.setRenderer(this);
        setContentView(glView);
        
        glGraphics = new GLGraphics(glView);
        fileIO = new AndroidFileIO(getAssets());
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, glView, 1, 1);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");        
    }
    /*
     * (non-Javadoc)
     * @see android.app.Activity#onResume()
     * ͨ�������onResume()������������Ⱦ�߳�
     * Ҳͨ��acquire()�������WakeLock
     */
    public void onResume() {
        super.onResume();
        glView.onResume();
        wakeLock.acquire();
    }
    /*
     * @see android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.microedition.khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
     * ���Д��Ƿ��ǵ�һ�Ά��ӣ����ǣ��t����getStartScreen()������������Ϸ�Ŀ�ʼ����
     * �����ǣ���״̬��Ϊ���У���Screen���ͻָ���startTime�������� 
     */
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {        
        glGraphics.setGL(gl);
        
        synchronized(stateChanged) {
            if(state == GLGameState.Initialized)
                screen = getStartScreen();
            state = GLGameState.Running;
            screen.resume();
            startTime = System.nanoTime();
        }        
    }
    
    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {        
    }

    @Override
    public void onDrawFrame(GL10 gl) {                
        GLGameState state = null;
        
        synchronized(stateChanged) {
            state = this.state;
        }
        //�����Ϸ���ڽ��У���ô�����ʱ��֪ͨScreen���и��¡���ʾ����
        if(state == GLGameState.Running) {
            float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
            startTime = System.nanoTime();
            
            screen.update(deltaTime);
            screen.present(deltaTime);
        }
        //�����Ϸ����������ôScreenҲ����ͣ��state��״̬����ΪGLGameState.Idle��
        //����֪ͨUI�߳��յ���ͣ������,ȷ����Ⱦ�̵߳���ͣ/�رն�ӦUI�߳��ϵĻ����ͣ/�ر�
        if(state == GLGameState.Paused) {
            screen.pause();            
            synchronized(stateChanged) {
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }
        /*
         * ����ڱ��رգ�Screen��ͣ���ر��Լ���Ȼ����ȴ���Ⱦ�߳���ɹرյ�UI�̷߳�����һ��֪ͨ
         */
        if(state == GLGameState.Finished) {
            screen.pause();
            screen.dispose();
            synchronized(stateChanged) {
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }            
        }
    }   
    /*
     * @see android.app.Activity#onPause()
     * �ȴ���Ⱦ���̴����µ�״̬�����ñ�׼��Java�ȴ�/֪ͨ(wait/notify)����ʵ����һ��
     */
    @Override 
    public void onPause() {        
        synchronized(stateChanged) {
            if(isFinishing())            
                state = GLGameState.Finished;
            else
                state = GLGameState.Paused;
            while(true) {
                try {
                    stateChanged.wait();
                    break;
                } catch(InterruptedException e) {         
                }
            }
        }
        wakeLock.release();
        glView.onPause();  
        super.onPause();
    }    
    
    public GLGraphics getGLGraphics() {
        return glGraphics;
    }  
    
    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Graphics getGraphics() {
        throw new IllegalStateException("We are using OpenGL!");
    }

    @Override
    public Audio getAudio() {
        return audio;
    }
    
    @Override
    public void setScreen(Screen screen) {
        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }
}
