package com.yjymorefunctions.model

import android.util.Log
import kotlin.properties.Delegates

/**
 * Auth：yujunyao
 * Since: 2017/11/29 上午11:16
 * Email：yujunyao@ylb.net
 */

class Guest {

    var age: Int by Delegates.observable(0) {
        prop, old, new -> Log.e("guest", "$old -> $new")
    }

    var gender: Int by Delegates.vetoable(0) {
        prop, old, new -> (old < new)//返回true才赋值
    }

}