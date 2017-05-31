package com.yjymorefunctions.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Auth：yujunyao
 * Since: 2017/5/26 下午3:21
 * Email：yujunyao@ylb.net
 */


public class Custom4View extends View {

    private Paint paintRedArc = new Paint();

    private Paint paintCircleArc = new Paint();

    private Paint paintYellowArc = new Paint();

    private Paint transparentPaintArc = new Paint();

    private Paint mPaint = new Paint();

    public Custom4View(Context context) {
        this(context, null);
    }

    public Custom4View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Custom4View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private int colorSweep[] = {
            Color.RED,
            Color.GREEN,
            Color.RED};
    private float colorPosition[] = {0.35f, 0.75f, 1.0f - 30 / 360.0f};

    private void init() {
        paintRedArc.setColor(Color.RED);
        paintRedArc.setStrokeJoin(Paint.Join.ROUND);
        paintRedArc.setStrokeCap(Paint.Cap.ROUND);
        paintRedArc.setStyle(Paint.Style.STROKE);
        paintRedArc.setAntiAlias(true);
        paintRedArc.setStrokeWidth(dp2px(10));

        paintCircleArc.setColor(Color.BLUE);
        paintCircleArc.setStrokeJoin(Paint.Join.ROUND);
        paintCircleArc.setStrokeCap(Paint.Cap.ROUND);
        paintCircleArc.setStyle(Paint.Style.STROKE);
        paintCircleArc.setAntiAlias(true);
        paintCircleArc.setStrokeWidth(dp2px(10));

        paintYellowArc.setColor(Color.WHITE);
        paintYellowArc.setStrokeJoin(Paint.Join.ROUND);
        paintYellowArc.setStrokeCap(Paint.Cap.ROUND);
        paintYellowArc.setStyle(Paint.Style.STROKE);
        paintYellowArc.setAntiAlias(true);
        paintYellowArc.setStrokeWidth(dp2px(10));

        transparentPaintArc.setColor(Color.WHITE);
        transparentPaintArc.setStrokeJoin(Paint.Join.ROUND);
        transparentPaintArc.setStrokeCap(Paint.Cap.ROUND);
        transparentPaintArc.setStyle(Paint.Style.STROKE);
        transparentPaintArc.setAntiAlias(true);
        transparentPaintArc.setStrokeWidth(dp2px(10));


    }

    private RectF rectF;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        rectF = new RectF(dp2px(10), dp2px(10), center[0] - dp2px(10), center[1] - dp2px(10));

//        drawRedArc(canvas);
//
//        drawYellowArc(canvas);
//
//        drawBlueArc(canvas);


        drawArc1(canvas);


        drawBlueArc(canvas);
    }


    private void drawArc1(Canvas canvas) {

        int layerID = canvas.saveLayer(0, 0, center[0], center[1], mPaint, Canvas.ALL_SAVE_FLAG);


        canvas.drawBitmap(makeRes(), 0, 0, mPaint);

//        if (redArcProgress >= redArcMax) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        } else {
//            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
//        }




        canvas.drawBitmap(makeDst(), 0, 0, mPaint);


        mPaint.setXfermode(null);

        canvas.restoreToCount(layerID);

    }


//
//    private int redArcProgress = 0;
    private int redArcMax = 330;
    private int currArc = 230;
//    private void drawRedArc(Canvas canvas) {
////        RectF rectF = new RectF(dp2px(10), dp2px(10), center[0] - dp2px(10), center[1] - dp2px(10));
//        canvas.drawArc(rectF, 230, redArcProgress, false, paintRedArc);
//    }
//
    private int startAngle = 205;
    private int endAngle = 218;
    private void drawBlueArc(Canvas canvas) {
        if (startAngle != 205) {
//            RectF rectF = new RectF(dp2px(10), dp2px(10), center[0] - dp2px(10), center[1] - dp2px(10));
            canvas.drawArc(rectF, startAngle, 0.4f, false, paintCircleArc);
        }
    }
//
//    private int startAngle1 = 0;
//    private int angleLength = 60;
//    private void drawYellowArc(Canvas canvas) {
//
//        if (redArcProgress >= 130) {
//            int layerID = canvas.saveLayer(0, 0, center[0], center[1], mPaint, Canvas.ALL_SAVE_FLAG);
//
//            canvas.drawBitmap(makeDst(), 0, 0, mPaint);
//
//
//            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//
//
//
//            canvas.drawBitmap(makeRes(), 0, 0, mPaint);
//
//            mPaint.setXfermode(null);
//
//            canvas.restoreToCount(layerID);
//        }
//    }

    private Canvas cv;
    private Bitmap makeDst() {

        SweepGradient sweepGradient = new SweepGradient(center[0], center[1], colorSweep, colorPosition);
        paintRedArc.setShader(sweepGradient);

        Bitmap bm = Bitmap.createBitmap(center[0], center[1], Bitmap.Config.ARGB_8888);

//        RectF rectF = new RectF(dp2px(10), dp2px(10), center[0] - dp2px(10), center[1] - dp2px(10));

        cv = new Canvas(bm);
        cv.rotate(currArc % 360, bm.getWidth() / 2, bm.getHeight() / 2);
        cv.drawArc(rectF, 0, 360, false, paintRedArc);



        return bm;
    }

    private Bitmap makeRes() {
        Bitmap bm = Bitmap.createBitmap(center[0], center[1], Bitmap.Config.ARGB_8888);

//        RectF rectF = new RectF(dp2px(10), dp2px(10), center[0] - dp2px(10), center[1] - dp2px(10));

        Canvas canvas = new Canvas(bm);

        canvas.drawArc(rectF, 230, redArcMax, false, paintYellowArc);

        return bm;
    }


    public void startAnimation() {
        runnable = new AnimationRunnable();

        postDelayed(new Runnable() {
            @Override
            public void run() {
                post(runnable);
            }
        }, 500);


    }

    private AnimationRunnable runnable;
    private class AnimationRunnable implements Runnable {

        @Override
        public void run() {

            currArc++;

            if (currArc >= 460) {
                if (startAngle < endAngle) {
                    startAngle++;
                }
            }


            postInvalidate();
            postDelayed(this, 5);
        }
    }

    private int[] center = new int[2];
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center[0] = w;
        center[1] = h;
    }

    private float dp2px(float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }
}
