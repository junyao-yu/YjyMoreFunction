package com.yjymorefunctions.utils;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Auth：yujunyao
 * Since: 2017/10/10 上午10:33
 * Email：yujunyao@ylb.net
 */


public class EasyBehaviour extends CoordinatorLayout.Behavior<TextView> {

    //带双参数的构造器必须重写
    public EasyBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //代表寻找被观察的View
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        //告知监听的dependency是button
        return dependency instanceof Button;
    }

    //当dependency（button）变化的时候可以对child(textview)进行操作
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        child.setX(dependency.getX()+200);
        child.setY(dependency.getY()+200);
        child.setText(dependency.getX()+","+dependency.getY());
        return true;
    }
}
