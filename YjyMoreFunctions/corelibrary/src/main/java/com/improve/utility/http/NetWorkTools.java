package com.improve.utility.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import okhttp3.OkHttpClient;

/**
 * Auth：yujunyao
 * Since: 2016/7/13 10:22
 * Email：yujunyao@yonglibao.com
 */
public class NetWorkTools {

    private static volatile NetWorkTools instance;
    private RequestQueue requestQueue;

    public NetWorkTools(Context context) {
        if(requestQueue != null) {
            requestQueue.stop();
        }
        requestQueue = Volley.newRequestQueue(context, new OkHttp3Stack(new OkHttpClient()));
    }

    public static NetWorkTools getInstance(Context context) {
        if(instance == null) {
            synchronized (NetWorkTools.class) {
                if(instance == null) {
                    instance = new NetWorkTools(context);
                }
            }
        }
        return instance;
    }

    public void addRequest(Request request, Object tag) {
        if(requestQueue != null) {
            request.setTag(tag);
            requestQueue.add(request);
        }
    }

    public void cancelRequest(Object tag) {
        if(requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }

    public void destory() {
        if(requestQueue != null) {
            requestQueue.stop();
            requestQueue = null;
        }
        instance = null;
    }

}
