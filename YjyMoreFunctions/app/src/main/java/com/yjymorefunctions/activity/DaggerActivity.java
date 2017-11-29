//package com.yjymorefunctions.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import com.yjymorefunctions.R;
//import com.yjymorefunctions.base.BaseActivity;
//import com.yjymorefunctions.model.ActivityComponent;
//import com.yjymorefunctions.model.DaggerActivityComponent;
//import com.yjymorefunctions.model.User;
//
//import javax.inject.Inject;
//
//import butterknife.Bind;
//
///**
// * Auth：yujunyao
// * Since: 2017/11/14 下午12:00
// * Email：yujunyao@ylb.net
// */
//
//
//public class DaggerActivity extends BaseActivity {
//
//    @Inject
//    User user;
//
//    @Bind(R.id.text)
//    TextView text;
//
//    @Override
//    protected void onInitParams(Intent intent) {
//
//    }
//
//    @Override
//    protected void setupViews(Bundle savedInstanceState) {
//
//        ActivityComponent activityComponent = DaggerActivityComponent.builder().build().inject(this);
//
//        text.setText(user.getName());
//
//    }
//
//    @Override
//    protected void onClickView(View view) {
//
//    }
//
//    @Override
//    protected int getLayoutResId() {
//        return R.layout.activity_dagger2;
//    }
//
//}
