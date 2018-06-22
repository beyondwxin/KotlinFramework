package com.king.kotlinframework.app

import android.app.Application
import com.blankj.utilcode.util.Utils

/**
 * Created by wuxin on 2018/6/22.
 */
class KotlinApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}