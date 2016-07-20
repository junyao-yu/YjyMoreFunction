package com.improve.utility.utils.upanddown;

/**
 * Auth：yujunyao
 * Since: 2016/7/15 16:11
 * Email：yujunyao@yonglibao.com
 */
public interface ProgressListener {

    /**下载或上传失败*/
    void executeFail();

    /**下载或长传成功*/
    void executeSuccess();

    /**下载或者上传进度*/
    void executeProgress(long bytesRead, long contentLength, boolean done);

}
