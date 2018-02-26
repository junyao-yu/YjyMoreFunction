package com.yjymorefunctions.utils

import android.app.Activity
import android.content.Intent
import com.yjymorefunctions.model.TestBundle

/**
 * Auth：yujunyao
 * Since: 2018/2/8 下午2:06
 * Email：yujunyao@ylb.net
 */

const val TRANSFER_DATA = "transfer"

inline fun <reified T: Activity> Activity.showActivity() {
    startActivity(Intent(this, T::class.java))
}

inline fun <reified T: Activity> Activity.showActivity(data: TestBundle) {
    var intent = Intent(this, T::class.java)
    intent.putExtra(TRANSFER_DATA, data)
    startActivity(intent)
}