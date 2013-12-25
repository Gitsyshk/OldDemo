package cy.rotateview.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Scroller;

@SuppressLint("DrawAllocation")
@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class CYRotateView extends ViewGroup {
	public interface CYRotateViewListener {
		void getRorateCurrentView(int item);
	}

	private static final int TOUCH_STATE_REST = 0;

	private static final int TOUCH_STATE_SCROLLING = 1;
	public static final int RORATE_HORIZONTAL = 2;
	public static final int RORATE_VERTICAL = 3;

	private Scroller mScroller;
	private int mTouchState = TOUCH_STATE_REST;
	private int mTouchSlop;

	private VelocityTracker mVelocityTracker;

	private static final int SNAP_VELOCITY = 600;

	private int mCurScreen;
	private int mPreScreen;

	private int mDefaultScreen = 0;

	private Camera mCamera;
	private Matrix mMatrix;

	private float angle = 90;
	private Context context;
	private int rotateDirecation;
	private boolean isNeedCirculate = true;
	private int min;
	private int max;
	private CYRotateViewListener listener;

	@SuppressLint("FloatMath")
	public CYRotateView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CYRotateView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
		mScroller = new Scroller(context);

		mCurScreen = mDefaultScreen;
		mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

		mCamera = new Camera();
		mMatrix = new Matrix();

		rotateDirecation = RORATE_HORIZONTAL;
	}

	public void setRotateViewListener(CYRotateViewListener listener) {
		this.listener = listener;

	}

	public void setRorateDirecation(int direcation) {
		this.rotateDirecation = direcation;
	}

	public void setIsNeedCirculate(boolean need) {
		this.isNeedCirculate = need;

	}

	public void setRoateAngle(float angle) {
		this.angle = angle;
	}

	public void rorateTo(int index) {
		if (mScroller.isFinished())
			snapToScreen(index);
		else
			snapToDestination();
	}

	public void rorateToNext() {
		if (mScroller.isFinished())
			snapToScreen(mCurScreen + 1);
		else
			snapToDestination();
	}

	public void rorateToPre() {
		if (mScroller.isFinished())
			snapToScreen(mCurScreen - 1);
		else
			snapToDestination();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		int cellWidthSpec = MeasureSpec.makeMeasureSpec(widthMeasureSpec,
				MeasureSpec.UNSPECIFIED);
		int cellHeightSpec = MeasureSpec.makeMeasureSpec(heightMeasureSpec,
				MeasureSpec.UNSPECIFIED);

		int count = getChildCount();

		for (int i = 0; i < count; i++) {
			View childView = getChildAt(i);
			/*
			 * This is called to find out how big a view should be. The parent
			 * supplies constraint information in the width and height
			 * parameters. The actual mesurement work of a view is performed in
			 * onMeasure(int, int), called by this method. Therefore, only
			 * onMeasure(int, int) can and must be overriden by subclasses.
			 */
			childView.measure(cellWidthSpec, cellHeightSpec);
		}

		setMeasuredDimension(
				resolveSize(widthMeasureSpec * count, widthMeasureSpec),
				resolveSize(heightMeasureSpec * count, heightMeasureSpec));
		// setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
		// super.onMeasure(widthMeasureSpec, heightMeasureSpec);

	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (changed) {
			int childLeft = 0;
			int childTop = 0;
			int childCount = getChildCount();
			if (isNeedCirculate)
				setHeadAndFoot();
			childCount = getChildCount();
			for (int i = 0; i < childCount; i++) {
				final View childView = getChildAt(i);
				// setLayoutParams(childView);
				if (childView.getVisibility() != View.GONE) {
					// childView.measure(r-l, b-t);
					final int childWidth = childView.getMeasuredWidth();
					int childHeight = childView.getMeasuredHeight();
					// childHeight = getHeight();
					if (rotateDirecation == RORATE_HORIZONTAL) {
						childView.layout(childLeft, 0, childLeft + childWidth,
								childHeight);

						childLeft += childWidth;
					} else if (rotateDirecation == RORATE_VERTICAL) {
						childView.layout(0, childTop, childWidth, childTop
								+ childHeight);
						childTop += childHeight;
					}

				}
			}
			if (isNeedCirculate) {
				min = 1;
				max = getChildCount() - 2;
				if (rotateDirecation == RORATE_HORIZONTAL) {
					scrollBy(getWidth(), 0);
				} else if (rotateDirecation == RORATE_VERTICAL) {
					scrollBy(0, getHeight());
				}
			} else {
				min = 0;
				max = getChildCount() - 1;
			}

		}
	}

	private void setHeadAndFoot() {
		ImageView img0 = new ImageView(context);
		ImageView img1 = new ImageView(context);
		View view0 = getChildAt(0);
		View view1 = getChildAt(getChildCount() - 1);
		setLayoutParams(view0);
		setLayoutParams(view1);
		img0.setImageBitmap(convertViewToBitmap(view0));
		img1.setImageBitmap(convertViewToBitmap(view1));
		addView(img1, 0);
		addView(img0);

	}

	private Bitmap convertViewToBitmap(View v) {
		v.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);
		v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight());
		v.buildDrawingCache();
		Bitmap bitmap = v.getDrawingCache();
		return bitmap;
	}

	private void setLayoutParams(View view) {
		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		layoutParams.height = getMeasuredHeight();
		layoutParams.width = getMeasuredWidth();
		view.setLayoutParams(layoutParams);

	}

	private void snapToScreen(int whichScreen) {
		whichScreen = (whichScreen >= getChildCount() - 1) ? getChildCount() - 1
				: whichScreen;
		switch (rotateDirecation) {
		case RORATE_HORIZONTAL:
			if (getScrollX() != whichScreen * getWidth()) {
				mCurScreen = whichScreen;
				final int delta = (int) (whichScreen * getWidth() - getScrollX());
				mScroller.startScroll(getScrollX(), 0, delta, 0,
						Math.abs(delta) * 2);
				if (isNeedCirculate) {
					if (mCurScreen == 0) {
						mScroller.startScroll(getWidth()
								* (getChildCount() - 2) - delta, 0, delta, 0,
								Math.abs(delta) * 2);
						mCurScreen = getChildCount() - 2;
					}
					if (mCurScreen == getChildCount() - 1) {
						mScroller.startScroll(getWidth() - delta, 0, delta, 0,
								Math.abs(delta) * 2);
						mCurScreen = 1;
					}
				}

				invalidate();
			}
			break;
		case RORATE_VERTICAL:
			if (getScrollY() != whichScreen * getHeight()) {
				mCurScreen = whichScreen;
				final int delta = (int) (whichScreen * getHeight() - getScrollY());
				mScroller.startScroll(0, getScrollY(), 0, delta,
						Math.abs(delta) * 2);
				if (isNeedCirculate) {
					if (mCurScreen == 0) {
						mScroller.startScroll(0, getHeight()
								* (getChildCount() - 2) - delta, 0, delta,
								Math.abs(delta) * 2);
						mCurScreen = getChildCount() - 2;
					}
					if (mCurScreen == getChildCount() - 1) {
						mScroller.startScroll(0, getHeight() - delta, 0, delta,
								Math.abs(delta) * 2);
						mCurScreen = 1;
					}
				}
				invalidate();
			}
			break;

		}
		if (this.mPreScreen != this.mCurScreen && listener != null) {
			this.mPreScreen = this.mCurScreen;
			int item = isNeedCirculate ? this.mCurScreen - 1 : this.mCurScreen;
			this.listener.getRorateCurrentView(item);
		}

	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {//
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			postInvalidate();
		}
	}

	/**
	 * 
	 */
	private void snapToDestination() {
		int destScreen = 0;
		if (rotateDirecation == RORATE_HORIZONTAL) {
			final int screenWidth = getWidth();
			//
			destScreen = (getScrollX() + screenWidth / 2) / screenWidth;
		} else if (rotateDirecation == RORATE_VERTICAL) {
			final int screenHeight = getHeight();
			//
			destScreen = (getScrollY() + screenHeight / 2) / screenHeight;
		}
		snapToScreen(destScreen);

	}

	private float mLastMotionY;
	private float mLastMotionX;

	/*
	 * 
	 */
	protected void dispatchDraw(Canvas canvas) {
		final long drawingTime = getDrawingTime();
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			drawScreen(canvas, i, drawingTime);
		}
	}

	/*
	 * 
	 */
	private void drawScreen(Canvas canvas, int screen, long drawingTime) {
		if (rotateDirecation == RORATE_HORIZONTAL) {

			final int width = getWidth();
			final int scrollHeight = screen * width;
			int scrollX = this.getScrollX();
			int faceIndex = screen;
			if (scrollHeight > scrollX + width
					|| scrollHeight + width < scrollX) {
				return;
			}
			final View child = getChildAt(faceIndex);
			final float currentDegree = scrollX * (angle / getMeasuredWidth());
			final float faceDegree = currentDegree - faceIndex * angle;
			if (faceDegree > 90 || faceDegree < -90) {
				return;
			}
			float centerX = (scrollHeight < scrollX) ? scrollHeight + width
					: scrollHeight;
			final float centerY = getHeight() / 2;
			final Camera camera = mCamera;
			final Matrix matrix = mMatrix;
			canvas.save();
			camera.save();
			camera.rotateY(-faceDegree);
			camera.getMatrix(matrix);
			camera.restore();
			matrix.preTranslate(-centerX, -centerY);
			matrix.postTranslate(centerX, centerY);
			canvas.concat(matrix);
			drawChild(canvas, child, drawingTime);
			canvas.restore();
		} else if (rotateDirecation == RORATE_VERTICAL) {

			final int height = getHeight();
			final int scrollHeight = screen * height;
			int scrollY = this.getScrollY();
			int faceIndex = screen;
			if (scrollHeight > scrollY + height
					|| scrollHeight + height < scrollY) {
				return;
			}
			final View child = getChildAt(faceIndex);
			final float currentDegree = scrollY * (angle / getMeasuredHeight());
			final float faceDegree = currentDegree - faceIndex * angle;
			if (faceDegree > 90 || faceDegree < -90) {
				return;
			}
			float centerY = (scrollHeight < scrollY) ? scrollHeight + height
					: scrollHeight;
			final float centerX = getWidth() / 2;
			final Camera camera = mCamera;
			final Matrix matrix = mMatrix;
			canvas.save();
			camera.save();
			camera.rotateX(faceDegree);
			camera.getMatrix(matrix);
			camera.restore();
			matrix.preTranslate(-centerX, -centerY);
			matrix.postTranslate(centerX, centerY);
			canvas.concat(matrix);
			drawChild(canvas, child, drawingTime);
			canvas.restore();
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mVelocityTracker == null) {

			mVelocityTracker = VelocityTracker.obtain();
		}

		mVelocityTracker.addMovement(event);

		final int action = event.getAction();
		final float y = event.getY();
		final float x = event.getX();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (!mScroller.isFinished()) {
				mScroller.abortAnimation();
			}
			mLastMotionX = x;
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			int deltaY = (int) (mLastMotionY - y);
			int deltaX = (int) (mLastMotionX - x);
			mLastMotionY = y;
			mLastMotionX = x;
			if (rotateDirecation == RORATE_HORIZONTAL)
				scrollBy(deltaX, 0);
			else if (rotateDirecation == RORATE_VERTICAL)
				scrollBy(0, deltaY);
			break;

		case MotionEvent.ACTION_UP:
			// if (mTouchState == TOUCH_STATE_SCROLLING) {
			final VelocityTracker velocityTracker = mVelocityTracker;

			velocityTracker.computeCurrentVelocity(1000);

			int velocityY = (int) velocityTracker.getYVelocity();
			int velocityX = (int) velocityTracker.getXVelocity();
			if ((velocityY > SNAP_VELOCITY
					&& rotateDirecation == RORATE_VERTICAL || velocityX > SNAP_VELOCITY
					&& rotateDirecation == RORATE_HORIZONTAL)
					&& mCurScreen > min) {
				// Fling enough to move left
				snapToScreen(mCurScreen - 1);
			} else if ((velocityY < -SNAP_VELOCITY
					&& rotateDirecation == RORATE_VERTICAL || velocityX < -SNAP_VELOCITY
					&& rotateDirecation == RORATE_HORIZONTAL)
					&& mCurScreen < max) {
				// Fling enough to move right
				snapToScreen(mCurScreen + 1);
			} else {
				snapToDestination();
			}

			if (mVelocityTracker != null) {
				mVelocityTracker.recycle();
				mVelocityTracker = null;
			}
			// }
			mTouchState = TOUCH_STATE_REST;
			break;
		case MotionEvent.ACTION_CANCEL:
			mTouchState = TOUCH_STATE_REST;
			break;
		}

		return true;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub

		final int action = ev.getAction();
		if ((action == MotionEvent.ACTION_MOVE)
				&& (mTouchState != TOUCH_STATE_REST)) {
			return true;
		}

		final float x = ev.getX();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionX = x;
			mTouchState = mScroller.isFinished() ? TOUCH_STATE_REST
					: TOUCH_STATE_SCROLLING;
			break;
		case MotionEvent.ACTION_MOVE:
			final int xDiff = (int) Math.abs(mLastMotionX - x);
			if (xDiff > mTouchSlop) {
				mTouchState = TOUCH_STATE_SCROLLING;
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
			mTouchState = TOUCH_STATE_REST;
			break;
		}
		return mTouchState != TOUCH_STATE_REST;
	}

}
