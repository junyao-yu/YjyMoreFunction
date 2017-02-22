package com.yjymorefunctions.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Auth：yujunyao
 * Since: 2016/7/13 15:17
 * Email：yujunyao@yonglibao.com
 */
public class MainActivity extends BaseActivity {

    private ComponentName mDefault;
    private ComponentName mDouble1Temp;
    private ComponentName mDouble1Back;
    private PackageManager mPm;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        titleView.setLeftImg(R.drawable.tip_no_news_icon);
        titleView.setLeftTip("消息");
        EventBus.getDefault().register(this);

        int[] arr  = bubbleSort(array);

        mDefault = getComponentName();
        mDouble1Temp = new ComponentName(getBaseContext(), "com.yjymorefunctions.activity.Test1");
        mDouble1Back = new ComponentName(getBaseContext(), "com.yjymorefunctions.activity.Test2");
        mPm = getApplicationContext().getPackageManager();
    }

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void sfdfdsfdf(String msg) {
        Toast.makeText(this, "MainActivity--->" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.btn_share_preference, R.id.btn_http, R.id.btn_upanddown, R.id.btn_loader_image, R.id.btn_touch_event, R.id.btn_fragment
    ,R.id.btn_vertical_scroll, R.id.btn_storage_in_app, R.id.btn_rxjava_android, R.id.btn_greendao, R.id.btn_ruler, R.id.btn_behaviour
    ,R.id.btn_gradient, R.id.btn_sgz, R.id.btn_thread_pool, R.id.btn_dialog_fragment, R.id.btn_sroller, R.id.btn_window, R.id.btn_draw
    ,R.id.btn_replace_icon, R.id.btn_replace_icon_temp})
    @Override
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_share_preference:
                startActivity(new Intent(MainActivity.this, SharePreferenceActivity.class));
                break;
            case R.id.btn_http:
                startActivity(new Intent(MainActivity.this, HttpActivity.class));
                break;
            case R.id.btn_upanddown:
                startActivity(new Intent(MainActivity.this, UpDownActivity.class));
                break;
            case R.id.btn_loader_image:
                startActivity(new Intent(MainActivity.this, ImageActivity.class));
                break;
            case R.id.btn_touch_event:
                startActivity(new Intent(MainActivity.this, TestTouchEventActivity.class));
                break;
            case R.id.btn_fragment:
                startActivity(new Intent(MainActivity.this, DisplayFragmentActivity.class));
                break;
            case R.id.btn_vertical_scroll:
                startActivity(new Intent(MainActivity.this, VerticalScrollActivity.class));
                break;
            case R.id.btn_storage_in_app:
                startActivity(new Intent(MainActivity.this, StorageInAppActivity.class));
                break;
            case R.id.btn_rxjava_android:
                startActivity(new Intent(MainActivity.this, RxJavaAndroidActivity.class));
                break;
            case R.id.btn_greendao:
                startActivity(new Intent(MainActivity.this, GreenDaoActivity.class));
                break;
            case R.id.btn_ruler:
                startActivity(new Intent(MainActivity.this, RulerActivity.class));
                break;
            case R.id.btn_behaviour:
                startActivity(new Intent(MainActivity.this, BehaviourActivity.class));
                break;
            case R.id.btn_gradient:
                startActivity(new Intent(MainActivity.this, GradientActivity.class));
                break;
            case R.id.btn_sgz:
                startActivity(new Intent(MainActivity.this, SgzActivity.class));
                break;
            case R.id.btn_thread_pool:
                startActivity(new Intent(MainActivity.this, ThreadPoolActivity.class));
                break;
            case R.id.btn_dialog_fragment:
                startActivity(new Intent(MainActivity.this, DisplayDialogFragment.class));
                break;
            case R.id.btn_sroller:
                startActivity(new Intent(MainActivity.this, ScrollerActivity.class));
                break;
            case R.id.btn_window:
                startActivity(new Intent(MainActivity.this, WindowActivity.class));
                break;
            case R.id.btn_draw:
//                startActivity(new Intent(MainActivity.this, DrawActivity.class));
                startActivityForResult(new Intent(MainActivity.this, DrawActivity.class), 22);
                break;
            case R.id.btn_replace_icon:
                change1Icon();
                break;
            case R.id.btn_replace_icon_temp:
                change2Icon();
                break;
        }
    }

    private void change1Icon() {
        disableComponent(mDefault);
        disableComponent(mDouble1Back);
        enableComponent(mDouble1Temp);
    }

    private void change2Icon() {
        disableComponent(mDefault);
        disableComponent(mDouble1Temp);
        enableComponent(mDouble1Back);
    }

    private void enableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    private void disableComponent(ComponentName componentName) {
        mPm.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }


    private int[] array = {95, 45, 15, 78, 84, 51, 24, 12};

    private int[] bubbleSort(int[] array) {
        int temp = 0;
        for(int i = array.length - 1;i > 0;i--) {
            for(int j = 0;j < i;j++) {
                if(array[j + 1] < array[j]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("requestCode--->", requestCode + "");
        Log.e("resultCode--->", resultCode + "");
    }
}
