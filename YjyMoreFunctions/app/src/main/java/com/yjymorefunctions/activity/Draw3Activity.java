package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;
import com.yjymorefunctions.views.Custom4View;

import butterknife.Bind;

/**
 * Auth：yujunyao
 * Since: 2017/5/26 下午3:19
 * Email：yujunyao@ylb.net
 */


public class Draw3Activity extends BaseActivity {
    @Bind(R.id.custom4_view)
    Custom4View custom4View;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        custom4View.startAnimation();
    }

    @Override
    protected void onClickView(View view) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_draw3;
    }

}
