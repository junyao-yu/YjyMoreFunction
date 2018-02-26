package com.yjymorefunctions.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yjymorefunctions.R
import com.yjymorefunctions.base.BaseActivity
import com.yjymorefunctions.model.TestBundle
import com.yjymorefunctions.utils.TRANSFER_DATA

/**
 * Auth：yujunyao
 * Since: 2018/2/8 下午2:28
 * Email：yujunyao@ylb.net
 */

class DisplayDataActivity : BaseActivity() {

    companion object {
        val name: String = "name"
        var data: String = "data"
    }

    override fun onInitParams(intent: Intent?) {
        var testBundle = intent?.getSerializableExtra(TRANSFER_DATA) as TestBundle
        var tempName = testBundle.map[name]
        var subBunble = testBundle.map[data]
    }

    override fun setupViews(savedInstanceState: Bundle?) {
    }

    override fun onClickView(view: View?) {
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_data
    }
}