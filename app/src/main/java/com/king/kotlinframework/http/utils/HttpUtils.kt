package com.king.kotlinframework.http.utils

import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.ToastUtils
import com.king.kotlinframework.app.Constants
import com.king.kotlinframework.http.LifeSubscription
import com.king.kotlinframework.http.Stateful
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by wuxin on 2018/6/22.
 * 单例模式-HttpUtils
 * 使用object关键字
 */
object HttpUtils {
    operator fun <T> invoke(lifecycle: LifeSubscription, observable: Observable<T>, callback: Callback<T>) {
        var target: Stateful? = null
        if (lifecycle is Stateful) {
            target = lifecycle
            callback.mTarget = target
        }
        if (!NetworkUtils.isConnected()) {
            ToastUtils.showShort("网络连接已断开")
            if (target != null) {
                target.setState(Constants.STATE_ERROR)
            }
            return
        }
        callback.setLifeSubscription(lifecycle)
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(callback)

    }
}