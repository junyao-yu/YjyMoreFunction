package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * Auth：yujunyao
 * Since: 2016/7/26 14:00
 * Email：yujunyao@yonglibao.com
 */
public class TestEventBusActivity extends BaseActivity {

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_eventbus);
//        ButterKnife.bind(this);
//        EventBus.getDefault().post("你好啊");
//        EventBus.getDefault().postSticky("啦啦啦");
    }

    @OnClick(R.id.btn)
    @Override
    protected void onClickView(View view) {
        //粘性事件，能够收到订阅之前发送的消息。但是它只能收到最新的一次消息
        EventBus.getDefault().postSticky("啦啦啦");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_eventbus;
    }
}
