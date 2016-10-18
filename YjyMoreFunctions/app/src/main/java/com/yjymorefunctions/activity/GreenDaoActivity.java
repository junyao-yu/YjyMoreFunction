package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;
import com.yjymorefunctions.model.User;
import com.yjymorefunctions.utils.DBManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Auth：yujunyao
 * Since: 2016/10/18 16:53
 * Email：yujunyao@yonglibao.com
 */
public class GreenDaoActivity extends BaseActivity {
    @Bind(R.id.display)
    TextView display;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {

    }

    @OnClick({R.id.insert, R.id.insert_array, R.id.delete, R.id.update, R.id.query})
    @Override
    protected void onClickView(View view) {
        String content = "";
        switch (view.getId()) {
            case R.id.insert:
                DBManager.getInstance(this).insertUser(new User(1, "yjy", 28));
                break;
            case R.id.insert_array:
                List<User> list = new ArrayList<>();
                for(int i=2;i<7;i++) {
                    list.add(new User(i, "lalala", 27));
                }
                DBManager.getInstance(this).insertUserList(list);
                break;
            case R.id.delete:
//                DBManager.getInstance(this).deleteUser(new User(4, "lalala", 27));
                DBManager.getInstance(this).deleteCondition(4);
                break;
            case R.id.update:
                DBManager.getInstance(this).updateUser(new User(3, "hahaha", 27));
                break;
            case R.id.query:
                List<User> userList = DBManager.getInstance(this).queryUserList();
                for(int i=0;i<userList.size();i++) {
                    content += userList.get(i).getId() + "," +  userList.get(i).getName() + "," + userList.get(i).getAge() + "\n";
                }
                display.setText(content);
                break;
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_greendao;
    }


}
