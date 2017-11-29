package com.yjymorefunctions.model

import android.util.Log
import com.yjymorefunctions.`interface`.Base

/**
 * Auth：yujunyao
 * Since: 2017/11/29 上午10:47
 * Email：yujunyao@ylb.net
 */

class Baseimpl(var x: Int) : Base {

    override fun print() {
        //TODO代码要注释掉，否则会抱异常
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.e("BaseImpl -> ", "${x}")
    }

}