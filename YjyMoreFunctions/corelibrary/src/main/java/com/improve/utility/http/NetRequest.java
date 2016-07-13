package com.improve.utility.http;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;

import java.util.Map;

/**
 * Auth：yujunyao
 * Since: 2016/7/13 11:38
 * Email：yujunyao@yonglibao.com
 */
public class NetRequest<T> extends Request<T> {
    private static final int TIME_OUT = 15000;

    public NetRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
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
        return null;
    }

    @Override
    protected void deliverResponse(T response) {

    }
}
