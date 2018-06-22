package com.king.kotlinframework.app
/**
 * Created by wuxin on 2018/6/22.
 */
interface Constants{
    //companion：伴生对象，类似于static关键字修饰静态变量
    companion object {
        /**
         * 网络请求状态
         */
        val STATE_UNKNOWN = 1002
        val STATE_LOADING = 1003
        val STATE_ERROR = 1004
        val STATE_EMPTY = 1005
        val STATE_SUCCESS = 1006
    }
}