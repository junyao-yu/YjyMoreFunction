package com.yjymorefunctions.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2016/12/6 10:37
 * Email：yujunyao@yonglibao.com
 */

public class WindowActivity extends BaseActivity {

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_click, R.id.btn_bottom})
    @Override
    protected void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_click:
                action();
                break;
            case R.id.btn_bottom:
                Toast.makeText(this, "没有被阻挡点击事件", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /**
     * http://blog.csdn.net/feiyangxiaomi/article/details/48736013
     */

    private void action() {
        // 获取Service
        WindowManager mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

//        ImageView imageView = new ImageView(this);
//        imageView.setImageResource(R.mipmap.ic_launcher);

        // 设置窗口类型，一共有三种Application windows, Sub-windows, System windows
        // API中以TYPE_开头的常量有23个
        WindowManager.LayoutParams mWindowParams = new WindowManager.LayoutParams();
//        mWindowParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT ;
//        // 设置期望的bitmap格式
//        mWindowParams.format = PixelFormat.RGBA_8888;
        mWindowParams.format = PixelFormat.TRANSLUCENT;
        mWindowParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL;
        mWindowParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

        // 以下属性在Layout Params中常见重力、坐标，宽高
        mWindowParams.gravity = Gravity.LEFT | Gravity. TOP;
        mWindowParams.x = 100;
        mWindowParams.y = 100;

        mWindowParams .width = WindowManager.LayoutParams. WRAP_CONTENT;
        mWindowParams .height = WindowManager.LayoutParams. WRAP_CONTENT;

        // 添加指定视图
        mWindowManager.addView(LayoutInflater.from(this).inflate(R.layout.toast_show, null), mWindowParams);
    }

//    private void show() {
//        if(views == null) {
//            views = LayoutInflater.from(this).inflate(R.layout.toast_show, null);
//            getWindowManagerGlobal().addView(views, getLayoutParam());
//        }else {
//            getWindowManagerGlobal().updateViewLayout(views, getLayoutParam());
//        }
//    }
//    private static View views = null;
//    private static WindowManager mWindowManager = null;
//    private static WindowManager.LayoutParams wmParams = null;
//    private WindowManager getWindowManagerGlobal() {
//        if(mWindowManager == null) {
//            mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//        }
//        return mWindowManager;
//    }
//
//    private WindowManager.LayoutParams getLayoutParam(){
//        if(wmParams==null){
//            //为windowManager.layoutParams添加type和flag,这样发送广播才不会出现token is null这样的错误
//            wmParams = new WindowManager.LayoutParams();
//            wmParams.windowAnimations = 0;
//            wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
//            wmParams.format = PixelFormat.RGBA_8888;
////            wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//            wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
//                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
//            wmParams.gravity = Gravity.TOP;
//        }
//        wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
//        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        return wmParams;
//    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_window;
    }

}
