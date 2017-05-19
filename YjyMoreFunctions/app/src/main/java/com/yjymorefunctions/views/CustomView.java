package com.yjymorefunctions.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Auth：yujunyao
 * Since: 2016/12/20 14:36
 * Email：yujunyao@yonglibao.com
 *
 * http://blog.csdn.net/harvic880925/article/details/50995587
 */

public class CustomView extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mWritePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mWritePath = new Path();
    private float preX;
    private float preY;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(5);

        mWritePaint.setStyle(Paint.Style.STROKE);
        mWritePaint.setColor(Color.RED);
        mWritePaint.setStrokeWidth(5);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e("xxxxx--->", "onAttachedToWindow");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.e("xxxxx--->", "onWindowFocusChanged");
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e("xxxxx--->", "onDetachedFromWindow");
    }

    private int allWidth = 0;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("xxxxx--->", "onDraw");
        allWidth = getWidth() * 3 / 2;
        drawBezier(canvas);

//        drawWrite(canvas);
    }

    private void drawBezier(Canvas canvas) {
        Path mPath = new Path();
        mPath.moveTo(-startX, getHeight());
        mPath.lineTo(-startX, getHeight() - 200);
        mPath.quadTo(allWidth / 4 - startX, getHeight() - 350, allWidth / 2 - startX, getHeight() - 200);
        mPath.quadTo(allWidth * 3 / 4 - startX, getHeight() - 50, allWidth - startX, getHeight() - 200);
        mPath.lineTo(allWidth - startX, getHeight());
        canvas.drawPath(mPath, mPaint);
    }

    private int startX = 0;

    public void startAnim(){
//        ValueAnimator animator = ValueAnimator.ofInt(0,allWidth - getWidth());
//        animator.setDuration(2000);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//
//                startX = (int)animation.getAnimatedValue();
//                Log.e("xxxxx---->", startX + "");
//                postInvalidate();
//            }
//        });
//        animator.start();
        post(new AnimateRunnable());
    }

    private boolean bolang1Flag = true;

    private class AnimateRunnable implements Runnable {

        @Override
        public void run() {
            if (startX <= 0) {
                startX += 5;
                bolang1Flag = true;
            } else if (startX >= allWidth - getWidth()) {
                startX -= 5;
                bolang1Flag = false;
            } else {
                if (bolang1Flag) {
                    startX += 5;
                } else {
                    startX -= 5;
                }
            }
            postInvalidate();
            postDelayed(this, 50);
        }
    }


//    private void drawWrite(Canvas canvas) {
//        canvas.drawPath(mWritePath, mWritePaint);
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                preX = event.getX();
//                preY = event.getY();
//                mWritePath.moveTo(preX, preY);
//                return true;
//            case MotionEvent.ACTION_MOVE:
//                float endX = (event.getX() + preX) / 2;
//                float endY = (event.getY() + preY) / 2;
//                mWritePath.quadTo(preX, preY, endX, endY);
//                preX = event.getX();
//                preY = event.getY();
//                invalidate();
//                break;
//            default:
//                break;
//        }
//        return super.onTouchEvent(event);
//    }
}
