package com.yjymorefunctions.model

/**
 * Auth：yujunyao
 * Since: 2018/2/27 上午10:16
 * Email：yujunyao@ylb.net
 */
//密封类
//一个密封类是自身抽象的，它不能直接实例化并可以有抽象（abstract）成员
//密封类不允许有非-private 构造函数（其构造函数默认为 private）
sealed class SealedBean

data class Const(val number: Double) : SealedBean()