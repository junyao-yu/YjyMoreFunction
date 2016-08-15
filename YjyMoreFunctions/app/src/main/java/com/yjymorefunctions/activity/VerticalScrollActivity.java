package com.yjymorefunctions.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Auth：yujunyao
 * Since: 2016/8/15 12:01
 * Email：yujunyao@yonglibao.com
 */
public class VerticalScrollActivity extends BaseActivity {
    @Bind(R.id.vertical_scroll_text)
    TextSwitcher verticalScrollText;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            verticalScrollText.setText(array[index]);
            index++;
            if (index == array.length) {
                index = 0;
            }
        }
    };

    private String[] array = new String[]{"AAAAA", "BBBBB", "CCCCC", "DDDDD", "EEEEE"};
    private int index = 0;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_vertical_scroll);
        ButterKnife.bind(this);

        verticalScrollText.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(VerticalScrollActivity.this);
                textView.setSingleLine();
                textView.setTextSize(15);
                textView.setTextColor(Color.WHITE);
                textView.setEllipsize(TextUtils.TruncateAt.END);
                FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
                );
                lp.gravity = Gravity.CENTER;
                textView.setLayoutParams(lp);
                return textView;
            }
        });

        new myThread().start();
    }

    private class myThread extends Thread {
        @Override
        public void run() {
            super.run();
            while (index < array.length) {
                try {
                    synchronized (this) {
                        mHandler.sendEmptyMessage(0);
                        this.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        index = -1;
    }

    @Override
    protected void onClickView(View view) {

    }

}
