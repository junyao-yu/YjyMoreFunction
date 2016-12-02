package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

/**
 * Auth：yujunyao
 * Since: 2016/11/21 10:53
 * Email：yujunyao@yonglibao.com
 */

public class BehaviourActivity extends BaseActivity {

    private Handler handler = new Handler(){
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
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "FAB", Snackbar.LENGTH_LONG).setAction("cancel", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e("cancel--->", "onclick cancel");
                    }
                }).show();
            }
        });
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

    @Override
    protected void onClickView(View view) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_behaviour;
    }
}
