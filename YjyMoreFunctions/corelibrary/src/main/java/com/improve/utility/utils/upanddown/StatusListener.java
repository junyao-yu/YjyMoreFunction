package com.improve.utility.utils.upanddown;

/**
 * Auth：yujunyao
 * Since: 2016/7/15 16:11
 * Email：yujunyao@yonglibao.com
 */
public interface StatusListener {

    /**下载或上传失败*/
    void responseFail();

    /**下载或长传成功*/
    void responseSuccess();

}
