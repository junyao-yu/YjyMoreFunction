package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;
import com.yjymorefunctions.views.MyLinearLayout;
import com.yjymorefunctions.views.MyView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2016/8/3 11:34
 * Email：yujunyao@yonglibao.com
 *
 * 可以参考此blog  http://www.cnblogs.com/jqyp/archive/2012/04/25/2469758.html
 */
public class TestTouchEventActivity extends BaseActivity {

    private static final String TAG = "TestTouchEventActivity";
    @Bind(R.id.outer_ll)
    MyLinearLayout outerLl;
    @Bind(R.id.inner_view)
    MyView innerView;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_touch_event);
        ButterKnife.bind(this);
        outerLl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(MyLinearLayout.TAG, "onTouch");

                /***
                 *设置为true的事件打印结果
                 * 08-03 14:02:45.157 22297-22297/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
                 08-03 14:02:45.157 22297-22297/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
                 08-03 14:02:45.157 22297-22297/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
                 08-03 14:02:45.157 22297-22297/com.yjymorefunctions I/MyLinearLayout: onTouch
                 08-03 14:02:45.224 22297-22297/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
                 08-03 14:02:45.224 22297-22297/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
                 08-03 14:02:45.224 22297-22297/com.yjymorefunctions I/MyLinearLayout: onTouch

                 设置为false的事件打印结果
                 08-03 14:12:55.869 27434-27434/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
                 08-03 14:12:55.869 27434-27434/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
                 08-03 14:12:55.869 27434-27434/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
                 08-03 14:12:55.869 27434-27434/com.yjymorefunctions I/MyLinearLayout: onTouch
                 08-03 14:12:55.871 27434-27434/com.yjymorefunctions I/MyLinearLayout: onTouchEvent
                 08-03 14:12:55.907 27434-27434/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
                 08-03 14:12:55.907 27434-27434/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
                 08-03 14:12:55.907 27434-27434/com.yjymorefunctions I/MyLinearLayout: onTouch
                 08-03 14:12:55.907 27434-27434/com.yjymorefunctions I/MyLinearLayout: onTouchEvent
                 08-03 14:12:55.919 27434-27434/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
                 08-03 14:12:55.919 27434-27434/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
                 08-03 14:12:55.919 27434-27434/com.yjymorefunctions I/MyLinearLayout: onTouch
                 08-03 14:12:55.919 27434-27434/com.yjymorefunctions I/MyLinearLayout: onTouchEvent
                 08-03 14:12:55.919 27434-27434/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
                 08-03 14:12:55.919 27434-27434/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
                 08-03 14:12:55.919 27434-27434/com.yjymorefunctions I/MyLinearLayout: onTouch
                 08-03 14:12:55.919 27434-27434/com.yjymorefunctions I/MyLinearLayout: onTouchEvent
                 08-03 14:12:55.920 27434-27434/com.yjymorefunctions I/MyLinearLayout: onclick
                 */

                return false;
            }
        });

        /**点击这个方块的事件打印结果
         *
         * 设置为false的时候 （执行下面的OnTouchEvent事件->OnClick事件被OnTouchEvent事件调用）
         * 08-03 14:25:22.955 5249-5249/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
         08-03 14:25:22.955 5249-5249/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
         08-03 14:25:22.955 5249-5249/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
         08-03 14:25:22.955 5249-5249/com.yjymorefunctions I/MyView: dispatchTouchEvent
         08-03 14:25:22.955 5249-5249/com.yjymorefunctions I/MyView: onTouch
         08-03 14:25:22.956 5249-5249/com.yjymorefunctions I/MyView: onTouchEvent
         08-03 14:25:22.974 5249-5249/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
         08-03 14:25:22.974 5249-5249/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
         08-03 14:25:22.974 5249-5249/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
         08-03 14:25:22.974 5249-5249/com.yjymorefunctions I/MyView: dispatchTouchEvent
         08-03 14:25:22.974 5249-5249/com.yjymorefunctions I/MyView: onTouch
         08-03 14:25:22.974 5249-5249/com.yjymorefunctions I/MyView: onTouchEvent
         08-03 14:25:23.014 5249-5249/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
         08-03 14:25:23.014 5249-5249/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
         08-03 14:25:23.014 5249-5249/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
         08-03 14:25:23.014 5249-5249/com.yjymorefunctions I/MyView: dispatchTouchEvent
         08-03 14:25:23.014 5249-5249/com.yjymorefunctions I/MyView: onTouch
         08-03 14:25:23.014 5249-5249/com.yjymorefunctions I/MyView: onTouchEvent
         08-03 14:25:23.015 5249-5249/com.yjymorefunctions I/MyView: onclick

         设置为true的时候（不执行下面的OnTouchEvent事件）
         08-03 14:34:21.440 11677-11677/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
         08-03 14:34:21.440 11677-11677/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
         08-03 14:34:21.440 11677-11677/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
         08-03 14:34:21.440 11677-11677/com.yjymorefunctions I/MyView: dispatchTouchEvent
         08-03 14:34:21.440 11677-11677/com.yjymorefunctions I/MyView: onTouch
         08-03 14:34:21.496 11677-11677/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
         08-03 14:34:21.496 11677-11677/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
         08-03 14:34:21.496 11677-11677/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
         08-03 14:34:21.496 11677-11677/com.yjymorefunctions I/MyView: dispatchTouchEvent
         08-03 14:34:21.496 11677-11677/com.yjymorefunctions I/MyView: onTouch
         */
        innerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(MyView.TAG, "onTouch");
                return false;
            }
        });
    }

    @OnClick({R.id.outer_ll, R.id.inner_view})
    @Override
    protected void onClickView(View view) {
        switch (view.getId()) {
            case R.id.outer_ll:
                Log.i(MyLinearLayout.TAG, "onclick");
                break;
            case R.id.inner_view:
                Log.i(MyView.TAG, "onclick");
                break;
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent");
        return super.onTouchEvent(event);
    }

}
