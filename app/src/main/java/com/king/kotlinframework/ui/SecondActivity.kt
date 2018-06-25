package com.king.kotlinframework.ui

import com.goat.kotlinbase.presenter.SecondActivityPresenter
import com.goat.kotlinbase.presenter.impl.SecondActivityPresenterImpl
import com.king.kotlinframework.R
import com.king.kotlinframework.app.Constants
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity(override val layoutId: Int = R.layout.activity_second) : BaseActivity<SecondActivityPresenterImpl>(), SecondActivityPresenter.View {
    override fun refreshView(mData: String) {
    }

    override fun initData() {
        mPresenter = SecondActivityPresenterImpl()

    }

    override fun loadData() {
        mPresenter!!.show("我是二页")
        setState(Constants.STATE_SUCCESS)
    }

    override fun initView() {
        tv.text = "哈哈哈"
        tv.setTextColor(this.resources.getColor(R.color.colorPrimaryDark))
    }

}
