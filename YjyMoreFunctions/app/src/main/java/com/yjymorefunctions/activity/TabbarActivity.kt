package com.yjymorefunctions.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yjymorefunctions.R
import com.yjymorefunctions.base.BaseActivity
import com.yjymorefunctions.views.BottomTabBar
import kotlinx.android.synthetic.main.activity_tabbar.*

/**
 * Auth：yujunyao
 * Since: 2018/3/12 下午6:09
 * Email：yujunyao@ylb.net
 */

class TabbarActivity : BaseActivity() {
    override fun onInitParams(intent: Intent?) {
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        tabbar.setData(object : BottomTabBar.SelectedCallBack {
            override fun selectRepeat(tag: Int) {

            }

            override fun selectOther(tag: Int) {

            }

        })
    }

    override fun onClickView(view: View?) {
    }

    override fun getLayoutResId(): Int {
        return R.layout.activity_tabbar
    }
}