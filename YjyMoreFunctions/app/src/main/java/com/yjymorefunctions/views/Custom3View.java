package com.yjymorefunctions.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.yjymorefunctions.R;


/**
 * Auth：yujunyao
 * Since: 2017/3/31 上午11:56
 * Email：yujunyao@yonglibao.com
 */


public class Custom3View extends View {

    private int[] center = new int[2];
    private int width = 0;
    private int height = 0;

    private Paint scalePaint = new Paint();//刻度
    private Paint textPaint = new Paint();//刻度分段文字
    private Paint arcPaint = new Paint();//圆弧
    private Paint gradientArcPaint = new Paint();//渐变圆弧

    private int startAngle = 160;//起始角度
    private int totalAngle = 220;//总共要经过的角度
    private int totalScale = 100;//总共刻度
    private float unitScale = 2.2f;

    private int outerDistance = 40;//刻度与外圈距离

    private int innerDistance = 10;//刻度与分数距离

    private String[] arrays = {"0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100"};
    private int section = 10;

    private int colorSweep[] = {
            getResources().getColor(R.color.color_ffac00),
            getResources().getColor(R.color.color_cbff00),
            getResources().getColor(R.color.color_00fcfb),
            getResources().getColor(R.color.color_00000000),
            getResources().getColor(R.color.color_ffac00)};
    private float colorPosition[] = {0.0f, 110 / 360.0f, 220 / 360.0f, 0.95f, 1.0f};


    public Custom3View(Context context) {
        this(context, null);
    }

    public Custom3View(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Custom3View(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        arcPaint.setColor(Color.WHITE);
        arcPaint.setStrokeJoin(Paint.Join.ROUND);
        arcPaint.setStrokeCap(Paint.Cap.ROUND);
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setAntiAlias(true);
        arcPaint.setStrokeWidth(dp2px(10));

        scalePaint.setColor(Color.WHITE);
        scalePaint.setStrokeWidth(dp2px(2));
        scalePaint.setAntiAlias(true);

        textPaint.setTextSize(sp2px(10));
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.WHITE);

        gradientArcPaint.setStrokeJoin(Paint.Join.ROUND);
        gradientArcPaint.setStrokeCap(Paint.Cap.ROUND);
        gradientArcPaint.setStyle(Paint.Style.STROKE);
        gradientArcPaint.setAntiAlias(true);
        gradientArcPaint.setStrokeWidth(dp2px(10));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawArc(canvas);

        drawGradientArc(canvas);

        drawScale(canvas);

        drawScaleText(canvas);
    }


    private void drawArc(Canvas canvas) {
        RectF rectF = new RectF(dp2px(10), dp2px(10), width - dp2px(10), height - dp2px(10));
        canvas.drawArc(rectF, startAngle, totalAngle, false, arcPaint);
    }

    private void drawGradientArc(Canvas canvas) {
        canvas.save();
        canvas.rotate(startAngle, center[0], center[1]);
        RectF rectF = new RectF(dp2px(10), dp2px(10), width - dp2px(10), height - dp2px(10));
        SweepGradient sweepGradient = new SweepGradient(center[0], center[1], colorSweep, colorPosition);
        gradientArcPaint.setShader(sweepGradient);
        canvas.drawArc(rectF, 0, totalAngle, false, gradientArcPaint);
        canvas.restore();
    }

    private void drawScale(Canvas canvas) {
        canvas.save();
        canvas.rotate(startAngle, center[0], center[1]);
        for (int i = 0; i < totalScale; i++) {
            canvas.drawLine(width - dp2px(25), center[1], width - dp2px(20), center[1], scalePaint);
            canvas.rotate(unitScale, center[0], center[1]);
        }
        canvas.restore();
    }

    private void drawScaleText(Canvas canvas) {
        RectF rectF = new RectF(dp2px(40), dp2px(40), width - dp2px(40), height - dp2px(40));
        Rect rect = new Rect();
        Path path = new Path();
        for (int i = 0; i < arrays.length; i++) {
            textPaint.getTextBounds(arrays[i], 0, arrays[i].length(), rect);
            // 粗略把文字的宽度视为圆心角2*θ对应的弧长，利用弧长公式得到θ，下面用于修正角度
            float angle = (float) (180 * rect.width() / 2 /
                    (Math.PI * (center[0] - dp2px(40) - rect.height())));

            path.reset();
            path.addArc(
                    rectF,
                    startAngle + i * (totalAngle / section) - angle, // 正起始角度减去θ使文字居中对准长刻度
                    totalAngle
            );
            canvas.drawTextOnPath(arrays[i], path, 0, 0, textPaint);
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                Resources.getSystem().getDisplayMetrics());
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
                Resources.getSystem().getDisplayMetrics());
    }

    /**
     * 使用布局文件XML创建CustomView时，在xml文件加载完成后调用这个方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        center[0] = w / 2;
        center[1] = h / 2;
        width = w;
        height = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
