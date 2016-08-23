package com.yjymorefunctions.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.improve.utility.utils.LogUtil;

/**
 * Auth：yujunyao
 * Since: 2016/8/23 11:31
 * Email：yujunyao@yonglibao.com
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksImp());
    }

    private class ActivityLifecycleCallbacksImp implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            LogUtil.i("onActivityCreated", activity.getLocalClassName());
        }

        @Override
        public void onActivityStarted(Activity activity) {
            LogUtil.i("onActivityStarted", activity.getLocalClassName());
        }

        @Override
        public void onActivityResumed(Activity activity) {
            LogUtil.i("onActivityResumed", activity.getLocalClassName());
        }

        @Override
        public void onActivityPaused(Activity activity) {
            LogUtil.i("onActivityPaused", activity.getLocalClassName());
        }

        @Override
        public void onActivityStopped(Activity activity) {
            LogUtil.i("onActivityStopped", activity.getLocalClassName());
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            LogUtil.i("onActivitySaveInstanceState", activity.getLocalClassName());
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            LogUtil.i("onActivityDestroyed", activity.getLocalClassName());
        }
    }

}
