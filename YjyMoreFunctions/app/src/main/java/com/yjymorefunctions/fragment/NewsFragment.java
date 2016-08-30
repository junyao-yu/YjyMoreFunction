package com.yjymorefunctions.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.improve.utility.utils.LogUtil;
import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Auth：yujunyao
 * Since: 2016/8/30 17:04
 * Email：yujunyao@yonglibao.com
 */
public class NewsFragment extends BaseFragment {
    @Bind(R.id.title)
    TextView title;
    private String titleStr = "";
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;

    public NewsFragment(String titleStr) {
        this.titleStr = titleStr;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, view);
        //XXX初始化view的各控件
        isPrepared = true;
        lazyLoad();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        LogUtil.i("NewsFragment", titleStr + isVisibleToUser);
    }

    @Override
    protected void lazyLoad() {
        if(!isPrepared || !isVisible) {
            return;
        }
        title.setText(titleStr);
        Toast.makeText(getActivity(), titleStr, Toast.LENGTH_LONG).show();
    }
}
