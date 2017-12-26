package com.yjymorefunctions.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import butterknife.Bind;

/**
 * Auth：yujunyao
 * Since: 2017/8/14 上午11:06
 * Email：yujunyao@ylb.net
 */


public class SpanTextActivity extends BaseActivity {
    @Bind(R.id.span)
    TextView span;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        String text="我是苏苏，打开另外一个Activity吧";
        SpannableString spannableString=new SpannableString(text);

        Drawable drawable = getResources().getDrawable(R.drawable.tip_no_news_icon);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        ImageSpan spanImage = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);

        spannableString.setSpan(spanImage, 5, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Toast.makeText(SpanTextActivity.this, "lalala", Toast.LENGTH_LONG).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        }, 5, text.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        span.setText(spannableString);
        span.setMovementMethod(LinkMovementMethod.getInstance());
        span.setHighlightColor(getResources().getColor(R.color.color_00000000));


        /**
         * ThreadLocal的简单引用，线程作用于，不同线程，不同数据副本
         */
        mThreadLocal.set("主线程");
        Log.e("print--->", mThreadLocal.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                mThreadLocal.set("线程1");
                Log.e("print--->", mThreadLocal.get());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                mThreadLocal.set("线程2");
                Log.e("print--->", mThreadLocal.get());
            }
        }).start();
    }

    @Override
    protected void onClickView(View view) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_span_text;
    }


    private ThreadLocal<String> mThreadLocal = new ThreadLocal<>();

}
