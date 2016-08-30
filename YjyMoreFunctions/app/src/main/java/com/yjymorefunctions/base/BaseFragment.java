package com.yjymorefunctions.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.improve.utility.utils.LogUtil;

/**
 * Auth：yujunyao
 * Since: 2016/8/9 17:57
 * Email：yujunyao@yonglibao.com
 */
public class BaseFragment extends Fragment {
    private static final String TAG = "BaseFragment";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogUtil.i(TAG, "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.i(TAG, "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtil.i(TAG, "onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.i(TAG, "onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i(TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.i(TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i(TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.i(TAG, "onDetach");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
//        LogUtil.i(TAG, "setUserVisibleHint isVisibleToUser = " + isVisibleToUser);
    }
}
