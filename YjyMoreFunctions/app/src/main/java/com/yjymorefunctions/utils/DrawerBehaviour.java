package com.yjymorefunctions.utils;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Auth：yujunyao
 * Since: 2017/10/10 上午11:15
 * Email：yujunyao@ylb.net
 */


public class DrawerBehaviour extends CoordinatorLayout.Behavior<TextView> {

    private int mFrameMaxHeight = 100;
    private int mStartY;

    public DrawerBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        Log.e("dependency--->", dependency.getY() + "");
        if(mStartY == 0) {
            mStartY = (int) dependency.getY();
        }
        //计算toolbar从开始移动到最后的百分比
        float percent = dependency.getY()/mStartY;
        Log.e("percent--->", percent + "");
        //改变child的坐标(从消失，到可见)
        child.setY(child.getHeight()*(1-percent) - child.getHeight());
        return true;
    }
}
