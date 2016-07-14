package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.yjymorefunctions.R;

/**
 * Auth：yujunyao
 * Since: 2016/7/13 15:17
 * Email：yujunyao@yonglibao.com
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_share_preference).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SharePreferenceActivity.class));
            }
        });

        findViewById(R.id.btn_http).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HttpActivity.class));
            }
        });
    }
}
