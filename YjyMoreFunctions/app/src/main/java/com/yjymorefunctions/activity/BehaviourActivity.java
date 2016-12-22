package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2016/11/21 10:53
 * Email：yujunyao@yonglibao.com
 */

public class BehaviourActivity extends BaseActivity {

    @Bind(R.id.depentent)
    TextView depentent;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("------>", (String) msg.obj);
        }
    };

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        titleView.setVisibility(View.GONE);
        /**
         * 注：产生一个Message对象，可以new  ，也可以使用Message.obtain()方法；两者都可以，
         * 但是更建议使用obtain方法，因为Message内部维护了一个Message池用于Message的复用，避免使用new 重新分配内存。
         */
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BehaviourActivity.this, "延时", Toast.LENGTH_SHORT).show();
            }
        }, 5000);
        handler.sendMessage(handler.obtainMessage(0, "不延时"));


    }
    @OnClick(R.id.depentent)
    @Override
    protected void onClickView(View view) {
        ViewCompat.offsetTopAndBottom(view, 5);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_behaviour;
    }

}
