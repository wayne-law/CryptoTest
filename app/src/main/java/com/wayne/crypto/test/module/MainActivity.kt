package com.wayne.crypto.test.module

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.wayne.crypto.test.R
import com.wayne.crypto.test.utils.SystemUiUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUiUtil.setLayoutStableFullScreen(window)
        //暂时默认深色状态栏
        SystemUiUtil.setLightStatusBar(this, false)
        setContentView(R.layout.activity_main)
        initEvent()
    }

    private fun initEvent() {
        bnv_tab.setOnNavigationItemSelectedListener {
            return@setOnNavigationItemSelectedListener when (it.itemId) {
                R.id.wallet -> true
                else -> {
                    Toast.makeText(this, "developing", Toast.LENGTH_SHORT).show()
                    false
                }
            }
        }
    }
}