package com.wayne.crypto.test.module

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wayne.crypto.test.R
import com.wayne.crypto.test.utils.SystemUiUtil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUiUtil.setLayoutStableFullScreen(window)
        //暂时默认深色状态栏
        SystemUiUtil.setLightStatusBar(this, false)
        setContentView(R.layout.activity_main)
    }
}