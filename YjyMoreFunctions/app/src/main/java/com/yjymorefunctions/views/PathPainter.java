package com.yjymorefunctions.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Auth：yujunyao
 * Since: 2017/10/12 下午5:43
 * Email：yujunyao@ylb.net
 * http://blog.csdn.net/eclipsexys/article/details/51992473
 */


public class PathPainter extends View {

    private Path mPath;
    private Paint mPaint;
    private PathMeasure mPathMeasure;
    private float mAnimatorValue;
    private Path mDst;
    private float mLength;

    public PathPainter(Context context) {
        this(context, null);
    }

    public PathPainter(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PathPainter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPathMeasure = new PathMeasure();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPath = new Path();
        mPath.addCircle(400, 400, 100, Path.Direction.CW);
        mPathMeasure.setPath(mPath, true);
        mLength = mPathMeasure.getLength();
        mDst = new Path();
        Log.e("mLength--->", mLength + "");//周长

        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mAnimatorValue = (float) valueAnimator.getAnimatedValue();
                Log.e("mAnimatorValue->", mAnimatorValue + "");
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDst.reset();
        // 硬件加速的BUG
        mDst.lineTo(0,0);
        float stop = mLength * mAnimatorValue;
        float start = (float) (stop - ((0.5 - Math.abs(mAnimatorValue - 0.5)) * mLength));
        Log.e("stop--->", stop + "");
        Log.e("start--->", start + "");
        mPathMeasure.getSegment(start, stop, mDst, true);
        canvas.drawPath(mDst, mPaint);
    }

}
