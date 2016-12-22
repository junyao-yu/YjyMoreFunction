package com.yjymorefunctions.views;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Auth：yujunyao
 * Since: 2016/12/22 14:03
 * Email：yujunyao@yonglibao.com
 */

public class MyBehavior extends CoordinatorLayout.Behavior {

    //必须重写此构造函数，源代码中通过反射调用此构造函数
    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //dependency有变化的view，child是behavior的view
        int offset = dependency.getTop() - child.getTop();
        ViewCompat.offsetTopAndBottom(child, offset);
        return true;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }
}
