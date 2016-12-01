package com.yjymorefunctions.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yjymorefunctions.R;
import com.yjymorefunctions.model.InvestList;

import java.util.ArrayList;
import java.util.List;

/**
 * Auth：yujunyao
 * Since: 2016/11/30 17:40
 * Email：yujunyao@yonglibao.com
 */

public class DateAdapter extends BaseExpandableListAdapter {

    private List<InvestList.Info> list = new ArrayList<>();
    private Context context;
    private int firstPosition = 0;

    public DateAdapter(Context context, List<InvestList.Info> list) {
        this.context = context;
        this.list = list;
    }

    public void setFirstPosition(int position) {
        if(this.firstPosition != position) {
            this.firstPosition = position;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return list.get(groupPosition).date_list.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition).date;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).date_list.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if(convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_head, null);
            groupViewHolder.circleIv = (ImageView) convertView.findViewById(R.id.item_head_circle);
            groupViewHolder.lineIv = (ImageView) convertView.findViewById(R.id.item_head_line);
            groupViewHolder.dateTv = (TextView) convertView.findViewById(R.id.item_head_date);
            convertView.setTag(groupViewHolder);
        }else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        if(firstPosition == groupPosition) {
            groupViewHolder.circleIv.setImageResource(R.drawable.invest_plan_circle_light);
            groupViewHolder.lineIv.setImageResource(R.drawable.invest_plan_line_light);
            groupViewHolder.dateTv.setBackgroundResource(R.drawable.invest_plan_date_light);
        }else {
            groupViewHolder.circleIv.setImageResource(R.drawable.invest_plan_circle_gray);
            groupViewHolder.lineIv.setImageResource(R.drawable.invest_plan_line_gray);
            groupViewHolder.dateTv.setBackgroundResource(R.drawable.invest_plan_date_gray);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if(convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_content, null);
            childViewHolder.circleIv = (ImageView) convertView.findViewById(R.id.item_content_circle);
            convertView.setTag(childViewHolder);
        }else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        if(firstPosition == groupPosition) {
            childViewHolder.circleIv.setBackgroundResource(R.drawable.bg_circle_3e87d7);
        }else {
            childViewHolder.circleIv.setBackgroundResource(R.drawable.bg_circle_bbbbbb);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public class GroupViewHolder {
        ImageView circleIv;
        ImageView lineIv;
        TextView dateTv;
    }

    public class ChildViewHolder {
        ImageView circleIv;
    }

}
