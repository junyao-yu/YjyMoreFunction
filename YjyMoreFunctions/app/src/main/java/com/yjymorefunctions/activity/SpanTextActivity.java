package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
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

    }

    @Override
    protected void onClickView(View view) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_span_text;
    }

}
