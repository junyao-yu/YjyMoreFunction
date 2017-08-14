package com.yjymorefunctions.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.yjymorefunctions.R
import kotlinx.android.synthetic.main.activity_kotlin.*


class kotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        main_tv.text = "Hello Kotlin!!!"
    }
}
