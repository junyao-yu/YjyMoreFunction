package com.yjymorefunctions.model

/**
 * Auth：yujunyao
 * Since: 2018/2/27 上午11:22
 * Email：yujunyao@ylb.net
 */

//out协变
interface Out<out T> {

    fun produce(): T//不能当做produce里面的入参处理

}