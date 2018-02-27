package com.yjymorefunctions.model

/**
 * Auth：yujunyao
 * Since: 2018/2/27 上午11:25
 * Email：yujunyao@ylb.net
 */

//逆变
interface In<in T> {

    fun produce(aa: T)//作为函数的入参

}