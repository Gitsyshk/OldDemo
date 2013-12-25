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
     * 用一个枚举记录GLGame实例当前的状态
     * Initialized	初始化
     * Running	运行中
     * Paused	暂停
     * Finished	完结
     * Idle		空闲
     */
	enum GLGameState {
        Initialized,
        Running,
        Paused,
        Finished,
        Idle
    }
    /*
     * 编写游戏所需要的一些实例
     * 记录状态的state
     * 用来同步UI线程和渲染线程的对象stateChanged
     * startTime记录时间差
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
     * 对一些对象的初始化；
     * 将该活动设为全屏；
     * 实例化GLSurfaceView并将其设为内容视图。
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
     * 通过自身的onResume()方法来启动渲染线程
     * 也通过acquire()方法获得WakeLock
     */
    public void onResume() {
        super.onResume();
        glView.onResume();
        wakeLock.acquire();
    }
    /*
     * @see android.opengl.GLSurfaceView.Renderer#onSurfaceCreated(javax.microedition.khronos.opengles.GL10, javax.microedition.khronos.egl.EGLConfig)
     * 先判斷是否是第一次啟動，若是，則调用getStartScreen()方法来返回游戏的开始画面
     * 若不是，则将状态设为运行，将Screen机型恢复，startTime进行重置 
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
        //如果游戏正在进行，那么计算耗时并通知Screen进行更新、显示自身
        if(state == GLGameState.Running) {
            float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
            startTime = System.nanoTime();
            
            screen.update(deltaTime);
            screen.present(deltaTime);
        }
        //如果游戏在那听，那么Screen也会暂停，state的状态设置为GLGameState.Idle，
        //用来通知UI线程收到暂停的请求,确保渲染线程的暂停/关闭对应UI线程上的活动的暂停/关闭
        if(state == GLGameState.Paused) {
            screen.pause();            
            synchronized(stateChanged) {
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }
        /*
         * 活动正在被关闭，Screen暂停并关闭自己，然后向等待渲染线程完成关闭的UI线程发送另一个通知
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
     * 等待渲染进程处理新的状态，采用标准的Java等待/通知(wait/notify)机制实现这一点
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
