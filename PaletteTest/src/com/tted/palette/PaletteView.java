package com.tted.palette;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class PaletteView extends SurfaceView implements Runnable,
		SurfaceHolder.Callback {
	private Paint mPaint = null;
	// 鐢绘澘鐨勫潗鏍囦互鍙婂楂�
	private int bgBitmapX;
	private int bgBitmapY;
	private int bgBitmapHeight = 150;
	private int bgBitmapWidth = 440;
	// 褰撳墠鐨勫凡缁忛�鎷╃殑鐢荤瑪鍙傛暟
	private int currentColor = Color.GRAY;
	private int currentSize = 10; // 1,3,5
	private int currentPaintIndex = -1;
	// 瀛樺偍鎵�湁鐨勫姩浣�
	private ArrayList<MyEraser> actionList = null;
	// 褰撳墠鐨勭敾绗斿疄渚�
	private MyEraser curAction = null;
	// 绾跨▼缁撴潫鏍囧織浣�
	boolean mLoop = true;
	SurfaceHolder mSurfaceHolder = null;

	Bitmap bgBitmap = null;

	Bitmap newbit = null;
	Bitmap logBitmap = null;
	private Canvas canvasTemp;

	/**
	 * 鍒濆鍖�
	 */
	public PaletteView(Context context, AttributeSet arr) {
		super(context, arr);
		Log.i("width", getWidth() + "ppp");
		mPaint = new Paint();
		actionList = new ArrayList<MyEraser>();
		mSurfaceHolder = this.getHolder();
		mSurfaceHolder.addCallback(this);
		this.setFocusable(true);
		mLoop = true;
		bgBitmap = ((BitmapDrawable) (getResources()
				.getDrawable(R.drawable.pic1))).getBitmap();
		logBitmap = ((BitmapDrawable) (getResources()
				.getDrawable(R.drawable.pic1))).getBitmap();
		newbit = Bitmap.createBitmap(bgBitmapWidth, bgBitmapHeight,
				Config.ARGB_4444);
		new Thread(this).start();
	}

	/**
	 * 灞忓箷浜嬩欢澶勭悊
	 */
	List<Integer> listx = new ArrayList<Integer>();
	List<Integer> listy = new ArrayList<Integer>();
	private boolean DO_NOT = true;

	public boolean onTouchEvent(MotionEvent event) {
		int antion = event.getAction();
		if (antion == MotionEvent.ACTION_CANCEL) {
			return false;
		}

		float touchX = event.getX();
		float touchY = event.getY();

		// 鐐瑰嚮鏃�
		if (antion == MotionEvent.ACTION_DOWN) {
			// 妫�祴鐐瑰嚮鐐规槸鍚﹀湪涓荤粯鍥惧尯
			if (testTouchMainPallent(touchX, touchY)) {
				setCurAction(getRealX(touchX), getRealY(touchY));
				clearSpareAction();
			}
		}
		// 鎷栧姩鏃�
		if (antion == MotionEvent.ACTION_MOVE) {
			int x = (int) getRealX(touchX);
			int y = (int) getRealY(touchY);
			if (curAction != null) {
				curAction.move(getRealX(touchX), getRealY(touchY));
			}
			if (DO_NOT) {

				if (!listx.contains(x) && x > 80 && x < 360) {
					listx.add(x);
				}
				if (!listy.contains(y) && y > 30 && y < 120) {
					listy.add(y);
				}
				if (listx.size() + listy.size() >= 200 && listx.size() > 100
						&& listy.size() > 40) {
					// Toast.makeText(getContext(), "鎭枩鎮ㄤ腑浜�00W澶у",
					// Toast.LENGTH_SHORT)
					// .show();
					new AlertDialog.Builder(getContext()).setTitle("涓鎻愮ず")
							.setMessage("鎭枩鎮ㄤ腑浜�00W澶у")
							.setPositiveButton("纭畾", null).show();
					DO_NOT = false;
				}
			}
			Log.i("bbb", listx.size() + "dfgd" + listy.size());
			Log.i("rrr", x + "-----" + y);
		}
		// 鎶捣鏃�
		if (antion == MotionEvent.ACTION_UP) {
			if (curAction != null) {
				curAction.move(getRealX(touchX), getRealY(touchY));
				actionList.add(curAction);
				currentPaintIndex++;
				curAction = null;
			}

		}
		return super.onTouchEvent(event);
	}

	/**
	 * 缁樺浘
	 */
	protected void Draw() {
		bgBitmapX = (getWidth() - bgBitmapWidth) / 2;
		bgBitmapY = (getHeight() - bgBitmapHeight) / 2;
		Canvas canvas = mSurfaceHolder.lockCanvas();
		if (mSurfaceHolder == null || canvas == null) {
			return;
		}
		// 濉厖鑳屾櫙
		// canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(logBitmap, 0, 0, null);
		// 鐢讳富鐢绘澘
		drawMainPallent(canvas);
		mSurfaceHolder.unlockCanvasAndPost(canvas);
	}

	/**
	 * 鐢讳富鐢绘澘
	 * 
	 * @param canvas
	 */
	private void drawMainPallent(Canvas canvas) {
		// 璁剧疆鐢荤瑪娌℃湁閿娇锛岀┖蹇�
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.STROKE);

		// 鐢绘澘缁樺浘鍖鸿儗鏅浘鐗�
		canvas.drawBitmap(bgBitmap, bgBitmapX, bgBitmapY, null);
		canvasTemp = new Canvas(newbit);
		canvasTemp.drawColor(Color.GRAY);

		for (int i = 0; i <= currentPaintIndex; i++) {
			actionList.get(i).draw(canvasTemp);
		}
		// 鐢诲綋鍓嶇敾绗旂棔杩�
		if (curAction != null) {
			curAction.draw(canvasTemp);
		}

		// 鍦ㄤ富鐢绘澘涓婄粯鍒朵复鏃剁敾甯冧笂鐨勫浘鍍�
		canvas.drawBitmap(newbit, bgBitmapX, bgBitmapY, null);
		// 鐢绘澘缁樺浘鍖鸿竟妗�
		mPaint.setColor(Color.GRAY);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeWidth(3);
		canvas.drawRect(bgBitmapX, bgBitmapY, bgBitmapX + bgBitmapWidth,
				bgBitmapY + bgBitmapHeight, mPaint);
	}

	/**
	 * 绾跨▼杩愯
	 */
	public void run() {
		while (mLoop) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (mSurfaceHolder) {
				Draw();
			}
		}
	}

	public void surfaceCreated(SurfaceHolder holder) {
		new Thread(this).start();
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		mLoop = false;
	}

	/**
	 * 妫�祴鐐瑰嚮浜嬩欢锛屾槸鍚﹀湪涓荤粯鍥惧尯
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean testTouchMainPallent(float x, float y) {
		if (x > bgBitmapX + 2 && y > bgBitmapY + 2
				&& x < bgBitmapX + bgBitmapWidth - 2
				&& y < bgBitmapY + bgBitmapHeight - 2) {
			return true;
		}

		return false;
	}

	/**
	 * 寰楀埌褰撳墠鐢荤瑪鐨勭被鍨嬶紝骞惰繘琛屽疄渚� *
	 * 
	 * @param x
	 * @param y
	 */
	public void setCurAction(float x, float y) {
		curAction = new MyEraser(x, y, currentSize, currentColor);
	}

	/**
	 * 鏍规嵁鎺ヨЕ鐐箈鍧愭爣寰楀埌鐢绘澘涓婂搴攛鍧愭爣
	 * 
	 * @param x
	 * @return
	 */
	public float getRealX(float x) {

		return x - bgBitmapX;
	}

	/**
	 * 鏍规嵁鎺ヨЕ鐐箉鍧愭爣寰楀埌鐢绘澘涓婂搴攜鍧愭爣
	 * 
	 * @param y
	 * @return
	 */
	public float getRealY(float y) {

		return y - bgBitmapY;
	}

	/**
	 * 璁剧疆鍥剧墖缂╂斁
	 * 
	 * @param bgBitmap
	 */
	public void setBgBitmap(Bitmap bgBitmap) {
		Matrix mMatrix = new Matrix();
		mMatrix.reset();
		int btWidth = bgBitmap.getWidth();
		int btHeight = bgBitmap.getHeight();
		float xScale = (float) bgBitmapWidth / btWidth;
		float yScale = (float) bgBitmapHeight / btHeight;
		mMatrix.postScale(xScale, yScale);
		this.bgBitmap = Bitmap.createBitmap(bgBitmap, 0, 0, btWidth, btHeight,
				mMatrix, true);
	}

	/**
	 * 璁剧疆鍥剧墖缂╂斁
	 * 
	 * @param bgBitmap
	 */
	public void setBgBitmap(Bitmap bgBitmap, int width, int height) {
		Matrix mMatrix = new Matrix();
		mMatrix.reset();
		int btWidth = bgBitmap.getWidth();
		int btHeight = bgBitmap.getHeight();
		float xScale = (float) width / btWidth;
		float yScale = (float) height / btHeight;
		mMatrix.postScale(xScale, yScale);
		this.logBitmap = Bitmap.createBitmap(bgBitmap, 0, 0, btWidth, btHeight,
				mMatrix, true);
	}

	/**
	 * 鍚庨�鍓嶈繘瀹屾垚鍚庯紝缂撳瓨鐨勫姩浣�
	 */
	private void clearSpareAction() {
		for (int i = actionList.size() - 1; i > currentPaintIndex; i--) {
			actionList.remove(i);
		}
	}

	/**
	 * 閲嶆柊甯冪疆鐢诲竷
	 */
	public void clearAction() {
		listx.clear();
		listy.clear();
		DO_NOT = true;
		currentPaintIndex = -1;
	}
}
