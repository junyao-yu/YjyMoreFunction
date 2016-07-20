package com.yjymorefunctions.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.improve.utility.AppManager;
import com.improve.utility.views.TitleView;
import com.yjymorefunctions.R;

/**
 * Auth：yujunyao
 * Since: 2016/7/15 10:40
 * Email：yujunyao@yonglibao.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    protected TitleView titleView;
    private FrameLayout baseContainer;
    private Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);
        AppManager.getAppManager().addActivity(this);

        initBaseView();
        initBaseData();

        /**intent不为空执行onInitParams函数，子类无需在此函数中判断intent是否为空，直接用即可*/
        if(null != getIntent()) {
            onInitParams(getIntent());
        }

        /**在此函数中设置layout配置文件，以及实例化views（ButterKnife即可，简化代码）*/
        setupViews(savedInstanceState);

    }

    /**********************************************子类必须或者有需要时重写的一些方法********************************************************/
    /**
     * activity传递的参数从这个函数获取
     * @param intent
     */
    protected abstract void onInitParams(Intent intent);

    /**
     * view配置文件设置在这里，以及一些其他的处理操作
     * @param savedInstanceState
     */
    protected abstract void setupViews(Bundle savedInstanceState);

    /**
     * view的点击事件
     * @param view
     */
    protected abstract void onClickView(View view);

    /**
     * 按左上角返回键，关闭activity
     * 如果子类有特殊需求需要屏蔽此功能的话，子类只需重写此函数即可
     */
    protected void functionKeyBack() {
        this.finish();
    }

    /**
     * 按物理键返回关闭activity
     * 如果子类有特殊需求需要屏蔽此功能的话，子类只需重写此函数即可
     */
    protected void physicalKeyBack() {
        this.finish();
    }

    /**********************************************重写activity的一些方法********************************************************/
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().removeActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            physicalKeyBack();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater.from(this).inflate(layoutResID, baseContainer);
    }

    /**********************************************activity初始化一些子类通用的方法********************************************************/
    protected void initBaseView() {
        baseContainer = (FrameLayout) findViewById(R.id.base_container);
        titleView = (TitleView) findViewById(R.id.base_title_view);
    }

    protected void initBaseData() {
        activity = this;
    }

}
