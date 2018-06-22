package com.goat.kotlinbase.presenter

import com.goat.kotlinbase.presenter.base.BaseView
import com.king.kotlinframework.bean.GankIoDataBean

/**
 * Created by wuxin on 2018/6/22.
 */

interface MainActivityPresenter {
    interface View : BaseView<List<GankIoDataBean.ResultBean>>

    interface Presenter {
        fun fetchGankIoData(page: Int, pre_page: Int)
    }
}


