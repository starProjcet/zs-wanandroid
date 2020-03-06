package com.example.zs_wan_android.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zs_wan_android.R

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }
}
