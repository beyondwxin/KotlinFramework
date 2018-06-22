package com.king.kotlinframework.http.utils

import android.util.Log
import com.blankj.utilcode.util.NetworkUtils
import com.blankj.utilcode.util.ToastUtils
import com.king.kotlinframework.bean.GankIoDataBean
import com.king.kotlinframework.app.Constants
import com.king.kotlinframework.http.LifeSubscription
import com.king.kotlinframework.http.Stateful
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.HttpException

/**
 * Created by wuxin on 2018/6/22.
 * 为网络请求提供一个主构造方法进行结果回调处理
 */
class Callback<T>(val mListener: HttpOnNextListener<Any?>) : Observer<T> {
    var mLifeSubscription: LifeSubscription? = null
    var mTarget: Stateful? = null

    fun detachView() {
        if (mTarget != null)
            mTarget = null
    }

    fun setLifeSubscription(lifecycle: LifeSubscription) {
        mLifeSubscription = lifecycle
    }

    override fun onNext(value: T) {
        if (value is GankIoDataBean) {
            onResponse(value)
        }
    }

    //添加Disposable内存泄漏
    override fun onSubscribe(d: Disposable?) {
        mLifeSubscription!!.bindSubscription(d!!)
    }

    override fun onError(e: Throwable?) {
        Log.e("kotlin","onError"+ e.toString())
    }

    override fun onComplete() {
        Log.e("kotlin","onComplete")
    }

    /**
     * 统一处理成功回调
     */
    fun onResponse(data: GankIoDataBean) {
        if (data == null) {
            onFail(Throwable())
            mListener.onError()
            return
        }
        if (data!!.isError){
            ToastUtils.showShort("请求失败")
            mListener.onError()
            return
        }
        //请求成功
        if (mTarget != null){
            mTarget!!.setState(Constants.STATE_SUCCESS)
            mListener!!.onNext(data.results!!)
        }
    }

    fun onFail(e: Throwable) {
        mListener.onError()
        //判断网络是否可用
        if (!NetworkUtils.isAvailableByPing()) {
            ToastUtils.showShort("你连接的网络有问题，请检查网络连接状态")
            if (mTarget != null){
                mTarget!!.setState(Constants.STATE_ERROR)
            }
            return
        }
        if (e is HttpException){
            mTarget!!.setState(Constants.STATE_ERROR)
            ToastUtils.showShort("服务器异常")
            return
        }
        mTarget!!.setState(Constants.STATE_ERROR)
        ToastUtils.showShort("数据解析异常")
    }

}