package com.yjymorefunctions.model

/**
 * Auth：yujunyao
 * Since: 2017/11/27 下午3:13
 * Email：yujunyao@ylb.net
 */

class A {

    var lastName: String = "junyao"
    get() = field.toUpperCase()

    var num: Int = 100
    get() = field
    set(value) {
        if (value < 10) {
            field = value
        } else {
            field = -1
        }
    }


}