package com.king.kotlinframework.http.utils

/**
 * Created by wuxin on 2018/6/22.
 * Http监听过程回调
 */
abstract class HttpOnNextListener<out T> {
    val t: T? = null
    /**
     * 成功后回调方法
     */
    abstract fun onNext(t: Any)

    /**
     * 缓存回调结果
     */
    fun onCacheNext(cache: String) {

    }

    /**
     * 失败或错误方法
     * 主动调用，更加灵活
     */
    fun onError() {

    }

    /**
     * 取消回调
     */
    fun onCancel() {

    }
}