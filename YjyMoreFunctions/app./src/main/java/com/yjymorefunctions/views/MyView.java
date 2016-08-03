package com.yjymorefunctions.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Auth：yujunyao
 * Since: 2016/8/3 14:20
 * Email：yujunyao@yonglibao.com
 */
public class MyView extends View {

    public static final String TAG = "MyView";

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i(TAG, "dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "onTouchEvent");
        /**
         * 返回true则表示该View能处理该事件，事件将终止向上传递（传递给其父View）,到这里事件传递就结束了，不会再往下传递
         *
         * 08-03 14:45:59.383 18941-18941/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
         08-03 14:45:59.384 18941-18941/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
         08-03 14:45:59.384 18941-18941/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
         08-03 14:45:59.384 18941-18941/com.yjymorefunctions I/MyView: dispatchTouchEvent
         08-03 14:45:59.384 18941-18941/com.yjymorefunctions I/MyView: onTouch
         08-03 14:45:59.384 18941-18941/com.yjymorefunctions I/MyView: onTouchEvent
         08-03 14:45:59.415 18941-18941/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
         08-03 14:45:59.415 18941-18941/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
         08-03 14:45:59.415 18941-18941/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
         08-03 14:45:59.415 18941-18941/com.yjymorefunctions I/MyView: dispatchTouchEvent
         08-03 14:45:59.415 18941-18941/com.yjymorefunctions I/MyView: onTouch
         08-03 14:45:59.415 18941-18941/com.yjymorefunctions I/MyView: onTouchEvent
         */
//        return true;

        /**返回false表示不能处理，则把事件传递给其父View的onTouchEvent()方法来处理
         *
         * 08-03 14:48:07.628 20138-20138/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
         08-03 14:48:07.628 20138-20138/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
         08-03 14:48:07.628 20138-20138/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
         08-03 14:48:07.628 20138-20138/com.yjymorefunctions I/MyView: dispatchTouchEvent
         08-03 14:48:07.628 20138-20138/com.yjymorefunctions I/MyView: onTouch
         08-03 14:48:07.628 20138-20138/com.yjymorefunctions I/MyView: onTouchEvent
         08-03 14:48:07.628 20138-20138/com.yjymorefunctions I/MyLinearLayout: onTouch
         08-03 14:48:07.628 20138-20138/com.yjymorefunctions I/MyLinearLayout: onTouchEvent
         08-03 14:48:07.672 20138-20138/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
         08-03 14:48:07.672 20138-20138/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
         08-03 14:48:07.673 20138-20138/com.yjymorefunctions I/MyLinearLayout: onTouch
         08-03 14:48:07.673 20138-20138/com.yjymorefunctions I/MyLinearLayout: onTouchEvent
         08-03 14:48:07.673 20138-20138/com.yjymorefunctions I/MyLinearLayout: onclick
         */
//        return false;

        /**
         * 系统默认的返回
         *
         * 08-03 14:50:46.855 22090-22090/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
         08-03 14:50:46.856 22090-22090/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
         08-03 14:50:46.856 22090-22090/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
         08-03 14:50:46.857 22090-22090/com.yjymorefunctions I/MyView: dispatchTouchEvent
         08-03 14:50:46.857 22090-22090/com.yjymorefunctions I/MyView: onTouch
         08-03 14:50:46.858 22090-22090/com.yjymorefunctions I/MyView: onTouchEvent
         08-03 14:50:46.882 22090-22090/com.yjymorefunctions I/TestTouchEventActivity: dispatchTouchEvent
         08-03 14:50:46.883 22090-22090/com.yjymorefunctions I/MyLinearLayout: dispatchTouchEvent
         08-03 14:50:46.883 22090-22090/com.yjymorefunctions I/MyLinearLayout: onInterceptTouchEvent
         08-03 14:50:46.883 22090-22090/com.yjymorefunctions I/MyView: dispatchTouchEvent
         08-03 14:50:46.883 22090-22090/com.yjymorefunctions I/MyView: onTouch
         08-03 14:50:46.883 22090-22090/com.yjymorefunctions I/MyView: onTouchEvent
         08-03 14:50:46.883 22090-22090/com.yjymorefunctions I/MyView: onclick
         */
        return super.onTouchEvent(event);
    }

}
