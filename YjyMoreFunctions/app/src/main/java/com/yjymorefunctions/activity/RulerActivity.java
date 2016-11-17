package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;
import com.yjymorefunctions.views.RulerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2016/11/16 10:34
 * Email：yujunyao@yonglibao.com
 */

public class RulerActivity extends BaseActivity {

    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.ruler_view)
    RulerView rulerView;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        rulerView.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void changeValue(RulerView rulerView, float value) {
                text.setText(value + "");
            }
        });
    }

    @OnClick({R.id.button1, R.id.button2})
    @Override
    protected void onClickView(View view) {
        switch (view.getId()) {
            case R.id.button1:
                rulerView.setScaleIndex(20);
                break;
            case R.id.button2:
                rulerView.setScaleIndex(30);
                break;
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_ruler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
