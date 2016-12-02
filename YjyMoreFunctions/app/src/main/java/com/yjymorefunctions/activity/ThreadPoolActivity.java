package com.yjymorefunctions.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yjymorefunctions.R;
import com.yjymorefunctions.base.BaseActivity;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Auth：yujunyao
 * Since: 2016/12/2 14:38
 * Email：yujunyao@yonglibao.com
 */

public class ThreadPoolActivity extends BaseActivity {

    private ThreadPoolExecutor threadPoolExecutor = null;

    @Override
    protected void onInitParams(Intent intent) {

    }

    @Override
    protected void setupViews(Bundle savedInstanceState) {
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                action();
            }
        });
        /**
         * 由于核心线程数为3，workQueue的大小为128，所以我们的线程的执行应该是先启动三个核心线程来执行任务，
         * 剩余的27个任务全部存在workQueue中，等待核心线程空余出来之后执行
         */
        threadPoolExecutor = new ThreadPoolExecutor(3, 10, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>(128));
        /**
         * AsyncTask 的配置线程池
         *
         * coorPollSize = Runtime.getRuntime().availableProcessors() + 1
         *
         * maximumPoolSize = 2 * coorPollSize + 1
         */

    }


    @Override
    protected void onClickView(View view) {

    }

    private void action() {
        for(int i=0;i<30;i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        Log.e("threadPoolExecutor--->", finalI + "");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPoolExecutor.execute(runnable);
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_thread_pool;
    }

    private class ThreadPoolExecutorExample extends ThreadPoolExecutor {

        public ThreadPoolExecutorExample(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
        }

    }

}
