package com.yjymorefunctions.views;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Auth：yujunyao
 * Since: 2016/12/22 16:44
 * Email：yujunyao@yonglibao.com
 */

public class ScrollBehavior extends CoordinatorLayout.Behavior {

    //必须重写此构造函数，源代码中通过反射调用此构造函数
    public ScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;//只关心垂直滑动
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        int leftScrolledY = target.getScrollY();
        child.setScrollY(leftScrolledY);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY) {
        ((NestedScrollView) child).fling((int) velocityY);
        return true;
    }
}
