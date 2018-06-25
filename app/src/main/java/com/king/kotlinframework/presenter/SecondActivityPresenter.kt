package com.goat.kotlinbase.presenter

import com.goat.kotlinbase.presenter.base.BaseView

/**
 * Created by wuxin on 2018/6/25.
 */

interface SecondActivityPresenter {
    interface View : BaseView<String>

    interface Presenter {
        fun show(msg: String)
    }
}


