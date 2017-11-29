package com.yjymorefunctions.model

import kotlin.reflect.KProperty

/**
 * Auth：yujunyao
 * Since: 2017/11/29 上午11:01
 * Email：yujunyao@ylb.net
 */

class DelegateProperty {

    var temp = "old"

    operator fun getValue(ref: Any?, p: KProperty<*>): String {
        return "DelegateProperty --> ${p.name} --> $temp"
    }

    operator fun setValue(ref: Any?, p: KProperty<*>, value: String) {
        temp = value
    }

}