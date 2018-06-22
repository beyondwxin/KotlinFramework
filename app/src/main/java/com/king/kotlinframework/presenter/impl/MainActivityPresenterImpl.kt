package com.goat.kotlinbase.presenter.impl

import android.util.Log
import com.goat.kotlinbase.http.ApiManager
import com.goat.kotlinbase.presenter.MainActivityPresenter
import com.king.kotlinframework.GankIoDataBean
import com.king.kotlinframework.http.utils.Callback
import com.king.kotlinframework.http.utils.HttpOnNextListener
import shinetechzz.com.vcleaders.presenter.base.BasePresenter
/**
 * Created by wuxin on 2018/6/22.
 */
class MainActivityPresenterImpl : BasePresenter<MainActivityPresenter.View>(), MainActivityPresenter.Presenter {
    override fun fetchGankIoData(page: Int, pre_page: Int) {
        val listener = object : HttpOnNextListener<List<GankIoDataBean.ResultBean>>() {
            override fun onNext(t: Any) {
                mView!!.refreshView(t as List<GankIoDataBean.ResultBean>)
            }
        }
        Log.e("parameter", "page:" + page + ",pre_page:"+ pre_page)
        invoke(ApiManager.instence.service.getGankIoData("Android", page, pre_page), Callback(listener))
    }

}