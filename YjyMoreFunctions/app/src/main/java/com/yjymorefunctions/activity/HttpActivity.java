package com.yjymorefunctions.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.improve.utility.http.NetRequest;
import com.improve.utility.http.NetWorkTools;
import com.improve.utility.utils.LogUtil;
import com.yjymorefunctions.R;
import com.yjymorefunctions.model.TestModel;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * Auth：yujunyao
 * Since: 2016/7/13 17:08
 * Email：yujunyao@yonglibao.com
 */
public class HttpActivity extends AppCompatActivity {
//    private static final String URL = "http://192.168.10.94:8080/Test/returnString";
    private static final String URL = "http://192.168.10.94:8080/Test/returnBean";
    private TextView tv;
    private View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http);

        tv = (TextView) findViewById(R.id.content);
        view = findViewById(R.id.display_palette);

        tv.setText(getString(R.string.test, "接着", 100));

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request<Object> request = new NetRequest<>(Request.Method.GET, URL, null, new TestModel(), new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(HttpActivity.this, "onErrorResponse", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.Listener<Object>(){

                    @Override
                    public void onResponse(Object response) {
                                                Toast.makeText(HttpActivity.this, "onResponse", Toast.LENGTH_SHORT).show();
                        TestModel testModel = (TestModel) response;

                        if(testModel.isStatus()) {
                            LogUtil.i("response--->", "true");
                        }else {
                            LogUtil.i("response--->", "false");
                        }
                    }
                });


                NetWorkTools.getInstance(HttpActivity.this).addRequest(request, HttpActivity.this);
            }
        });

        findViewById(R.id.btn_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HttpActivity.this, TestEventBusActivity.class));
            }
        });


        findViewById(R.id.btn_palette).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap  bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pic_teacher);
                Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                    @Override
                    public void onGenerated(Palette palette) {
                        //得到一个调色板，可以获取颜色
                        Palette.Swatch swatch = palette.getLightVibrantSwatch();//获取亮色
                        if (null != swatch) {
                            view.setBackgroundColor(swatch.getRgb());
                        }
                    }
                });
            }
        });
    }
    boolean bFlag = false;
    @Override
    protected void onResume() {
        super.onResume();
        if (bFlag) {
            EventBus.getDefault().register(this);
        }
        bFlag = true;
    }

    @Subscribe(threadMode = ThreadMode.MainThread, sticky = true)
    public void helloWrold(String msg) {
        Toast.makeText(this, "HttpActivity--->" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
