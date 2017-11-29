package com.yjymorefunctions.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Environment;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Collections;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;

/**
 * Auth：yujunyao
 * Since: 2016/8/23 11:31
 * Email：yujunyao@yonglibao.com
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //解决picasso加载某些https图片加载不出来，这里只要实力化能加载https的client网络请求就可以了。
        OkHttpClient client = new OkHttpClient.Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .cache(new Cache(new File(getStoragePath()), 50 * 1024 * 1024))//设置缓存路径
                .build();


        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(client)).build();
//        Picasso picasso = new Picasso.Builder(this).downloader(new OkHttp3Downloader(new File(getStoragePath()), 50 * 1024 * 1024)).build();
//        Picasso.setSingletonInstance(picasso);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacksImp());
    }

    public static String getStoragePath() {
        String storagePath = "";
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if(sdCardExist) {
            storagePath = Environment.getExternalStorageDirectory().getAbsolutePath() +File.separator+ "aaaaa";
            File file = new File(storagePath);
            if (!file.exists()) {
                file.mkdirs();
            }
        }
        return storagePath;
    }

    private class ActivityLifecycleCallbacksImp implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
//            LogUtil.i("onActivityCreated", activity.getLocalClassName());
        }

        @Override
        public void onActivityStarted(Activity activity) {
//            LogUtil.i("onActivityStarted", activity.getLocalClassName());
        }

        @Override
        public void onActivityResumed(Activity activity) {
//            LogUtil.i("onActivityResumed", activity.getLocalClassName());
        }

        @Override
        public void onActivityPaused(Activity activity) {
//            LogUtil.i("onActivityPaused", activity.getLocalClassName());
        }

        @Override
        public void onActivityStopped(Activity activity) {
//            LogUtil.i("onActivityStopped", activity.getLocalClassName());
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
//            LogUtil.i("onActivitySaveInstanceState", activity.getLocalClassName());
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
//            LogUtil.i("onActivityDestroyed", activity.getLocalClassName());
        }
    }

}
