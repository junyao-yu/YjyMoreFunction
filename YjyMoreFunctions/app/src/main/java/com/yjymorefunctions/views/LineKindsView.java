package com.yjymorefunctions.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Auth：yujunyao
 * Since: 2016/12/5 14:58
 * Email：yujunyao@yonglibao.com
 */

/**
 * PathEffect
 我们可以通过canvas.drawPath( )绘制一些简单的路径。但是假若需要给路径设置一些效果或者样式，这时候就要用到PathEffect了。

 PathEffect有如下几个子类：

 CornerPathEffect
 用平滑的方式衔接Path的各部分
 DashPathEffect
 将Path的线段虚线化
 PathDashPathEffect
 与DashPathEffect效果类似但需要自定义路径虚线的样式
 DiscretePathEffect
 离散路径效果
 ComposePathEffect
 两种样式的组合。先使用第一种效果然后在此基础上应用第二种效果
 SumPathEffect
 两种样式的叠加。先将两种路径效果叠加起来再作用于Path
 * */
public class LineKindsView extends View {
    public LineKindsView(Context context) {
        this(context, null);
    }

    public LineKindsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineKindsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(0,300);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(8);
        Path path = new Path();
        path.moveTo(15, 60);
        for (int i = 0; i <= 35; i++) {
            path.lineTo(i * 30, (float) (Math.random() * 150));
        }
        canvas.drawPath(path, paint);
        canvas.translate(0, 400);
        paint.setPathEffect(new CornerPathEffect(60));
        canvas.drawPath(path, paint);
        canvas.translate(0, 400);
        paint.setPathEffect(new DashPathEffect(new float[] {15, 8}, 1));
        canvas.drawPath(path, paint);
    }
}
