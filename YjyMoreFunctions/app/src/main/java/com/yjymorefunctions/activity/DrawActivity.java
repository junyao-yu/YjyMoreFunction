package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

/**
 * Auth：yujunyao
 * Since: 2016/12/20 14:35
 * Email：yujunyao@yonglibao.com
 */

public class DrawActivity extends BaseActivity {
    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setResult(100);
                finish();
            }
        }, 5_000);
    }

    @Override
    protected void onClickView(View view) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_draw;
    }
}
