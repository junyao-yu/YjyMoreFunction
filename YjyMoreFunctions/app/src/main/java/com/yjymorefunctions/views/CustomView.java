package com.yjymorefunctions.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
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
        mPaint.setStrokeWidth(5);

        mWritePaint.setStyle(Paint.Style.STROKE);
        mWritePaint.setColor(Color.RED);
        mWritePaint.setStrokeWidth(5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBezier(canvas);

        drawWrite(canvas);
    }

    private void drawBezier(Canvas canvas) {
        Path mPath = new Path();
        mPath.moveTo(140, 400);
        mPath.quadTo(340, 100, 540, 400);
        mPath.quadTo(740, 700, 940, 400);
        canvas.drawPath(mPath, mPaint);
    }

    private void drawWrite(Canvas canvas) {
        canvas.drawPath(mWritePath, mWritePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                preX = event.getX();
                preY = event.getY();
                mWritePath.moveTo(preX, preY);
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (event.getX() + preX) / 2;
                float endY = (event.getY() + preY) / 2;
                mWritePath.quadTo(preX, preY, endX, endY);
                preX = event.getX();
                preY = event.getY();
                invalidate();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
