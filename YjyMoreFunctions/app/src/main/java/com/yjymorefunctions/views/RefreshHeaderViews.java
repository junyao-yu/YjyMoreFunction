package com.yjymorefunctions.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yjymorefunctions.R;
import com.yjymorefunctions.views.swiperefresh.SwipeRefreshTrigger;
import com.yjymorefunctions.views.swiperefresh.SwipeTrigger;

/**
 * Auth：yujunyao
 * Since: 2017/4/10 下午3:29
 * Email：yujunyao@yonglibao.com
 */


public class RefreshHeaderViews extends LinearLayout implements SwipeRefreshTrigger, SwipeTrigger {

    private TextView statusText = null;

    public RefreshHeaderViews(Context context) {
        super(context);
    }

    public RefreshHeaderViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_header, this, true);
        statusText = (TextView) view.findViewById(R.id.pull_to_refresh_text);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }

    @Override
    public void onRefresh() {
        statusText.setText("正在刷新");
    }

    @Override
    public void onPrepare() {
    }

    @Override
    public void onMove(int yScrolled, boolean isComplete, boolean automatic) {
        if (!isComplete) {
            if (yScrolled >= getHeight()) {
                statusText.setText("释放刷新");
            } else {
                statusText.setText("下拉刷新");
            }
        } else {
            statusText.setText("正在刷新");
        }
    }

    @Override
    public void onRelease() {
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onReset() {
    }
}
