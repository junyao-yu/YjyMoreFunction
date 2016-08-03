package com.yjymorefunctions.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.improve.utility.storage.sp.SpUtil;
import com.yjymorefunctions.R;
import com.yjymorefunctions.utils.SpKey;

/**
 * Auth：yujunyao
 * Since: 2016/7/13 15:22
 * Email：yujunyao@yonglibao.com
 */
public class SharePreferenceActivity extends AppCompatActivity {
    private TextView tv;
    private int iType = 0;
    private SpUtil spUtil;
    private EditText et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        spUtil = new SpUtil(this);

        et = (EditText) findViewById(R.id.input);
        tv = (TextView) findViewById(R.id.content);

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iType = Integer.parseInt(et.getText().toString().trim());
                switch (iType) {
                    case 0:
                        spUtil.setBoolean(SpKey.TEST_BOOLEAN, true);
                        break;
                    case 1:
                        spUtil.setLong(SpKey.TEST_LONG, 123L);
                        break;
                    case 2:
                        spUtil.setFloat(SpKey.TEST_FLOAT, 123.00f);
                        break;
                    case 3:
                        spUtil.setInt(SpKey.TEST_INT, 123);
                        break;
                    case 4:
                        spUtil.setString(SpKey.TEST_STRING, "123");
                        break;
                }
            }
        });

        findViewById(R.id.btn_read).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iType = Integer.parseInt(et.getText().toString().trim());
                String content = "";
                switch (iType) {
                    case 0:
                        if(spUtil.getBoolean(SpKey.TEST_BOOLEAN)){
                            content = "真";
                        }else {
                            content = "假";
                        }
                        break;
                    case 1:
                        content = spUtil.getLong(SpKey.TEST_LONG) + "";
                        break;
                    case 2:
                        content = spUtil.getFloat(SpKey.TEST_FLOAT) + "";
                        break;
                    case 3:
                        content = spUtil.getInt(SpKey.TEST_INT) + "";
                        break;
                    case 4:
                        content = spUtil.getString(SpKey.TEST_STRING);
                        break;
                }
                tv.setText(content);
            }
        });
    }
}
