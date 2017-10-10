package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import butterknife.Bind;

/**
 * Auth：yujunyao
 * Since: 2017/10/10 上午10:12
 * Email：yujunyao@ylb.net
 */


public class Behaviour1Activity extends BaseActivity {
    @Bind(R.id.btn)
    Button btn;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        v.setX(event.getRawX() - v.getWidth() / 2);
                        v.setY(event.getRawY() - v.getHeight() / 2);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void onClickView(View view) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_behaviour1;
    }

}
