package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

/**
 * Auth：yujunyao
 * Since: 2016/11/21 10:53
 * Email：yujunyao@yonglibao.com
 */

public class BehaviourActivity extends BaseActivity {
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
    }

    @Override
    protected void onClickView(View view) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_behaviour;
    }
}
