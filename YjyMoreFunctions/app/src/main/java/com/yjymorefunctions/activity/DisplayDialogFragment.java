package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;
import com.yjymorefunctions.fragment.ExampleDialogFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2016/12/3 15:03
 * Email：yujunyao@yonglibao.com
 */
//Google官网不推荐使用Dialog
public class DisplayDialogFragment extends BaseActivity implements ExampleDialogFragment.ResponseListener{
    @Bind(R.id.btn_display)
    Button btnDisplay;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {

    }

    @OnClick({R.id.btn_display})
    @Override
    protected void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_display:
                showDialogFragment();
                break;
        }
    }


    private void showDialogFragment() {
        ExampleDialogFragment exampleDialogFragment = new ExampleDialogFragment();
        exampleDialogFragment.show(getSupportFragmentManager(), "Example");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_display_dialog_fragment;
    }

    @Override
    public void onResponse(String response) {
        Toast.makeText(this, response, Toast.LENGTH_SHORT).show();
    }
}
