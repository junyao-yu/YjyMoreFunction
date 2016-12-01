package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.google.gson.Gson;
import com.yjymorefunctions.R;
import com.yjymorefunctions.adapter.DateAdapter;
import com.yjymorefunctions.base.BaseActivity;
import com.yjymorefunctions.model.InvestList;
import com.yjymorefunctions.views.floatingexpandable.FloatingGroupExpandableListView;
import com.yjymorefunctions.views.floatingexpandable.WrapperExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Auth：yujunyao
 * Since: 2016/11/30 17:36
 * Email：yujunyao@yonglibao.com
 */

public class SgzActivity extends BaseActivity {

    @Bind(R.id.expandable)
    FloatingGroupExpandableListView expandable;
    private InvestList investList = new InvestList();
    DateAdapter dateAdapter;
    private List<InvestList.Info> list = new ArrayList<>();

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        String json = getResources().getString(R.string.text);
        investList = new Gson().fromJson(json, InvestList.class);
        list.addAll(investList.data.list);

        InvestList.Info info = new InvestList.Info();
        info.date = "2016-11-29";
        info.date_list = investList.data.list.get(0).date_list;
        List<InvestList.Info> listaa = new ArrayList<>();
        for(int i=0;i<5;i++) {
            listaa.add(info);
        }

        list.addAll(listaa);

        dateAdapter = new DateAdapter(this, list);
        WrapperExpandableListAdapter wrapperAdapter = new WrapperExpandableListAdapter(dateAdapter);
        expandable.setAdapter(wrapperAdapter);
        for (int i = 0, length = dateAdapter.getGroupCount(); i < length; i++) {
            expandable.expandGroup(i);
        }

        expandable.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        expandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });
        expandable.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                final ExpandableListView listView = (ExpandableListView) view;
                /**
                 * calculate point (0,0)
                 */
                int npos = view.pointToPosition(0, 0);// 其实就是firstVisibleItem
                if (npos == AdapterView.INVALID_POSITION)// 如果第一个位置值无效
                    return;

                long pos = listView.getExpandableListPosition(npos);
                int childPos = ExpandableListView.getPackedPositionChild(pos);// 获取第一行child的id
                int groupPos = ExpandableListView.getPackedPositionGroup(pos);// 获取第一行group的id
                dateAdapter.setFirstPosition(groupPos);
                Log.e("groupPos-->", groupPos + "");
            }
        });
    }

    @Override
    protected void onClickView(View view) {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_sgz;
    }
}
