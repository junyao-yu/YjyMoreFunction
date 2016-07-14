package com.improve.utility.http;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.HttpHeaderParser;

import java.util.Map;

/**
 * Auth：yujunyao
 * Since: 2016/7/13 11:38
 * Email：yujunyao@yonglibao.com
 */
public class NetRequest<T> extends Request<T> {
    private static final int TIME_OUT = 15000;
    private Response.Listener<T> successListener;
    private static final String POST_TAG = "";
    private Class clazz;

    public NetRequest(int method, String url, Map<String, String> bodyParams, Object object, Response.ErrorListener errorListener, Response.Listener<T> successListener) {
        super(method, url, errorListener);
        setShouldCache(false);
        clazz = object.getClass();
        this.successListener = successListener;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return super.getParams();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return super.getHeaders();
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        return new DefaultRetryPolicy(TIME_OUT, 0, 1.0f);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String data = new String(response.data,  HttpHeaderParser.parseCharset(response.headers));
            Log.i("data--->", data);
            if (!TextUtils.isEmpty(data)) {
                Log.i("step1--->", "1");
                T responseObject = (T) JSON.parseObject(data, clazz);
                Log.i("step1--->", "2");
                return Response.success(responseObject,
                        HttpHeaderParser.parseCacheHeaders(response));
            }else {
                return Response.error(new ParseError());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("Exception--->", e.getMessage());
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        if(successListener != null) {
            successListener.onResponse(response);
        }
    }
}
