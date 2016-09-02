package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import org.apache.http.util.EncodingUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2016/8/22 17:11
 * Email：yujunyao@yonglibao.com
 */
public class StorageInAppActivity extends BaseActivity {

    //定义文件的名称
    String fileName = "test.txt";
    //写入和读出的数据信息
    String message = "test in app cache";

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
//        setContentView(R.layout.activity_storage_in_app);
//        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_write, R.id.btn_read, R.id.btn_delete})
    @Override
    protected void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_read:
                Toast.makeText(this, readFileData(fileName), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_write:
                writeFileData(fileName, message);
                break;
            case R.id.btn_delete:
                deleteFileData(fileName);
                break;
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_storage_in_app;
    }

    //向指定的文件中写入指定的数据
    public void writeFileData(String filename, String message){
        try {
            FileOutputStream fout = openFileOutput(filename, MODE_PRIVATE);//获得FileOutputStream
            //将要写入的字符串转换为byte数组
            byte[]  bytes = message.getBytes();
            fout.write(bytes);//将byte数组写入文件
            fout.close();//关闭文件输出流
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //打开指定文件，读取其数据，返回字符串对象
    public String readFileData(String fileName){
        String result="";
        try {
            FileInputStream fin = openFileInput(fileName);
            //获取文件长度
            int lenght = fin.available();
            byte[] buffer = new byte[lenght];
            fin.read(buffer);
            //将byte数组转换成指定格式的字符串
            result = EncodingUtils.getString(buffer, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void deleteFileData(String fileName) {
        deleteFile(fileName);
    }

}
