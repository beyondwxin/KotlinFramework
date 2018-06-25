package com.goat.kotlinbase.presenter.impl

import com.blankj.utilcode.util.ToastUtils
import com.goat.kotlinbase.presenter.SecondActivityPresenter
import shinetechzz.com.vcleaders.presenter.base.BasePresenter

/**
 * Created by wuxin on 2018/6/22.
 */
class SecondActivityPresenterImpl : BasePresenter<SecondActivityPresenter.View>(), SecondActivityPresenter.Presenter {
    override fun show(msg: String) {
        ToastUtils.showShort(msg)
    }
}