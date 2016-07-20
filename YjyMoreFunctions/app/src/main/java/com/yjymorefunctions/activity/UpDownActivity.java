package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.improve.utility.utils.LogUtil;
import com.improve.utility.utils.upanddown.Download;
import com.improve.utility.utils.upanddown.ProgressListener;
import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2016/7/15 16:45
 * Email：yujunyao@yonglibao.com
 */
public class UpDownActivity extends BaseActivity {
    private String path;
    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        setContentView(R.layout.activity_updown);
        ButterKnife.bind(this);
        path = Environment.getExternalStorageDirectory().getPath();
    }

    @OnClick({R.id.btn_upload, R.id.btn_download})
    @Override
    protected void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_upload:

                break;
            case R.id.btn_download:
                //不显示进度
//                Download.downloadFile("http://a.hiphotos.baidu.com/zhidao/pic/item/f9198618367adab4e2174a2689d4b31c8601e494.jpg", path, "aaaaaa.jpg", new StatusListener() {
//                    @Override
//                    public void responseFail() {
//                        LogUtil.i("UpDownActivity", "responseFail");
//                    }
//
//                    @Override
//                    public void responseSuccess() {
//                        LogUtil.i("UpDownActivity", "responseSuccess");
//                    }
//                });

                //显示进度
                Download.downloadFile("http://a.hiphotos.baidu.com/zhidao/pic/item/f9198618367adab4e2174a2689d4b31c8601e494.jpg", path, "aaaaaa.jpg", new ProgressListener() {
                    @Override
                    public void executeFail() {
                        LogUtil.i("UpDownActivity", "executeFail");
                    }

                    @Override
                    public void executeSuccess() {
                        LogUtil.i("UpDownActivity", "executeSuccess");
                    }

                    @Override
                    public void executeProgress(long bytesRead, long contentLength, boolean done) {
                        LogUtil.i("UpDownActivity", "bytesRead-->" + bytesRead + "====contentLength--->" + contentLength + "======>" + done);
                    }
                });

                break;
        }
    }
}
