package com.improve.utility.utils.upanddown;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Auth：yujunyao
 * Since: 2016/7/15 16:08
 * Email：yujunyao@yonglibao.com
 */
public class Download {

    /**
     * 不需要知道下载进度
     * @param url
     * @param savePath
     * @param saveFileName
     * @param statusListener
     */
    public static void downloadFile(String url, final String savePath, final String saveFileName, final StatusListener statusListener) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                statusListener.responseFail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    File file = new File(savePath, saveFileName);
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    statusListener.responseSuccess();
                } catch (Exception e) {
                    statusListener.responseFail();
                } finally {
                    if(is != null) {
                        is.close();
                    }
                    if(fos != null) {
                        fos.close();
                    }
                }
            }
        });
    }


    /**
     * 需要知道下载进度
     * @param url
     * @param savePath
     * @param saveFileName
     * @param progressListener
     * @param progressListener
     */
    public static void downloadFile(String url, final String savePath, final String saveFileName, final ProgressListener progressListener) {

        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        ProgressHelper.addProgressResponseListener(okHttpClient, progressListener).newCall(request).enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                progressListener.executeFail();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    is = response.body().byteStream();
                    File file = new File(savePath, saveFileName);
                    fos = new FileOutputStream(file);
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    fos.flush();
                    progressListener.executeSuccess();
                } catch (Exception e) {
                    progressListener.executeFail();
                } finally {
                    if(is != null) {
                        is.close();
                    }
                    if(fos != null) {
                        fos.close();
                    }
                }
            }
        });
    }

}
