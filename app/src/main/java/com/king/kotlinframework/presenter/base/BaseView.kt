package com.goat.kotlinbase.presenter.base

/**
 * Created by wuxin on 2018/6/22.
 */
interface BaseView<in T> {
    fun refreshView(mData: T)//获取数据成功调用该方法。
}