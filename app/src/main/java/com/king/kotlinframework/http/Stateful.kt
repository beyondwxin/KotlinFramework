package com.king.kotlinframework.http

/**
 * Created by wuxin on 2018/6/22.
 * 控制显示状态
 */
open interface Stateful{
    fun setState(state: Int)
}