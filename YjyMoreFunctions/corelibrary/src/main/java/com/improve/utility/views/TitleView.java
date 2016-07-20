package com.improve.utility.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.improve.utility.R;

/**
 * Auth：yujunyao
 * Since: 2016/7/15 14:00
 * Email：yujunyao@yonglibao.com
 * 仅限UI设计仍然模仿老旧的IOS样式，如果是根据Android设计风格的，那当然MD的Toolbar最好
 */
public class TitleView extends LinearLayout {

    private RelativeLayout leftRl;
    private RelativeLayout middleRl;
    private RelativeLayout rightRl;
    private TextView leftBackTv;
    private TextView middleTitleTv;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        View titleView = LayoutInflater.from(context).inflate(R.layout.layout_title_view, this);
        leftRl = (RelativeLayout) titleView.findViewById(R.id.left_rl);
        middleRl = (RelativeLayout) titleView.findViewById(R.id.middle_rl);
        rightRl = (RelativeLayout) titleView.findViewById(R.id.right_rl);
        leftBackTv = (TextView) titleView.findViewById(R.id.left_back);
        middleTitleTv = (TextView) titleView.findViewById(R.id.middle_title);
    }

    /*****左边区域**/
    public void setLeftImg(int resID) {
        Drawable drawable = getResources().getDrawable(resID);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        leftBackTv.setCompoundDrawables(drawable, null, null, null);
    }

    public void setLeftTip(String tip) {
        leftBackTv.setText(tip);
    }

    public void setLeftTip(int resID) {
        leftBackTv.setText(resID);
    }

    public void setLeftTipsListener(OnClickListener listener) {
        leftBackTv.setOnClickListener(listener);
    }

    public void setLeftTipVisible(int isVisible) {
        leftBackTv.setVisibility(isVisible);
    }

    public void customLeftView(View view) {
        if(null != view) {
            leftRl.removeAllViews();
            leftRl.addView(view);
        }
    }

    /*****中间区域**/
    public void setMiddleTitle(String title) {
        middleTitleTv.setText(title);
    }

    public void setMiddleTitle(int resID) {
        middleTitleTv.setText(resID);
    }

    public void customMiddleView(View view) {
        if(null != view) {
            middleRl.removeAllViews();
            middleRl.addView(view);
        }
    }

    /*****右边区域**/
    public void customRightView(View view) {
        if(null != view) {
            rightRl.removeAllViews();
            rightRl.addView(view);
        }
    }

}
