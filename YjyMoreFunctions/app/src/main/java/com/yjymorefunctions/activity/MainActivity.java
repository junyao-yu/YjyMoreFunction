package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2016/7/13 15:17
 * Email：yujunyao@yonglibao.com
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_share_preference, R.id.btn_http})
    @Override
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_share_preference:
                startActivity(new Intent(MainActivity.this, SharePreferenceActivity.class));
                break;
            case R.id.btn_http:
                startActivity(new Intent(MainActivity.this, HttpActivity.class));
                break;
        }
    }
}
