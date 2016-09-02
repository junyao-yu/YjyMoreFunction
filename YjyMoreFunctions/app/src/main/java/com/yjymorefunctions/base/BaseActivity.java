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
import android.widget.Toast;

import com.improve.utility.AppManager;
import com.improve.utility.views.TitleView;
import com.yjymorefunctions.R;

import butterknife.ButterKnife;

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
        initBaseView();
        initBaseData();
        setContentView(getLayoutResId());
        ButterKnife.bind(this);

        AppManager.getAppManager().addActivity(this);



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

    protected abstract int getLayoutResId();

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


    /**
     * 当我们的应用程序正在运行时的回调
     *
     *     TRIM_MEMORY_RUNNING_MODERATE    表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经有点低了，
     *     系统可能会开始根据LRU缓存规则来去杀死进程了。
     TRIM_MEMORY_RUNNING_LOW    表示应用程序正常运行，并且不会被杀掉。但是目前手机的内存已经非常低了，
     我们应该去释放掉一些不必要的资源以提升系统的性能，同时这也会直接影响到我们应用程序的性能。
     TRIM_MEMORY_RUNNING_CRITICAL    表示应用程序仍然正常运行，但是系统已经根据LRU缓存规则杀掉了大部分缓存的进程了。
     这个时候我们应当尽可能地去释放任何不必要的资源，不然的话系统可能会继续杀掉所有缓存中的进程，并且开始杀掉一些本来应当保持运行的进程，比如说后台运行的服务。

     程序被缓存的回调类型
     TRIM_MEMORY_BACKGROUND    表示手机目前内存已经很低了，系统准备开始根据LRU缓存来清理进程。这个时候我们的程序在LRU缓存列表的最近位置，
     是不太可能被清理掉的，但这时去释放掉一些比较容易恢复的资源能够让手机的内存变得比较充足，从而让我们的程序更长时间地保留在缓存当中，
     这样当用户返回我们的程序时会感觉非常顺畅，而不是经历了一次重新启动的过程。
     TRIM_MEMORY_MODERATE    表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的中间位置，如果手机内存还得不到进一步释放的话，
     那么我们的程序就有被系统杀掉的风险了。
     TRIM_MEMORY_COMPLETE    表示手机目前内存已经很低了，并且我们的程序处于LRU缓存列表的最边缘位置，系统会最优先考虑杀掉我们的应用程序，
     在这个时候应当尽可能地把一切可以释放的东西都进行释放。
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        switch (level) {
            //TRIM_MEMORY_UI_HIDDEN回调只有当我们程序中的所有UI组件全部不可见的时候才会触发，
            // 这和onStop()方法还是有很大区别的，因为onStop()方法只是当一个Activity完全不可见的时候就会调用
            case TRIM_MEMORY_UI_HIDDEN:
                // 可以进行一些资源释放操作
                Toast.makeText(this, this.getLocalClassName(), Toast.LENGTH_SHORT).show();
                break;
        }
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
