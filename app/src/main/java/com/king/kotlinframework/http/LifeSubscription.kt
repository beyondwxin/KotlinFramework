package com.king.kotlinframework.http

import io.reactivex.disposables.Disposable
/**
 * Created by wuxin on 2018/6/22.
 */
interface LifeSubscription {
    fun bindSubscription(disposable: Disposable)
}