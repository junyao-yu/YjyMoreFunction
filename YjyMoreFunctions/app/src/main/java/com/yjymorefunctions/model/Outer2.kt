package com.yjymorefunctions.model

/**
 * Auth：yujunyao
 * Since: 2017/12/1 上午11:27
 * Email：yujunyao@ylb.net
 */

class Outer2 {

    val bar: Int = 1

    inner class Nested {
        fun foo() = bar//可以从外层类调用属性
    }

    /**
     * out 参数“from ”不是一个简单的数组，而是受限制的（限制为一个生产者，projected），它只能调用返回参数为T的方法，意味着只能调用“get()”函数。
     */
    fun display(from: Array<out String>, to: Array<in String>) {
//        from.set(0, "sss")
        to.set(0, "sss")
    }

}