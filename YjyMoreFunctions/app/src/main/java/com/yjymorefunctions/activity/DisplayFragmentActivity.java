package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;
import com.yjymorefunctions.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Auth：yujunyao
 * Since: 2016/8/9 18:12
 * Email：yujunyao@yonglibao.com
 */
public class DisplayFragmentActivity extends BaseActivity {
    @Bind(R.id.fragment_life)
    FrameLayout fragmentLife;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_display_fragment);
        ButterKnife.bind(this);

        switchFragment(new BaseFragment());
    }

    @Override
    protected void onClickView(View view) {

    }

    protected void switchFragment(Fragment pFrgmnt) {
        // 得到一个fragment 事务（类似sqlite的操作）
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_life, pFrgmnt);// 将得到的fragment
        // 替换当前的viewGroup内容，add则不替换会依次累加
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);// 设置动画效果
        ft.commitAllowingStateLoss();// 提交
        // ft.commit();
    }

}
