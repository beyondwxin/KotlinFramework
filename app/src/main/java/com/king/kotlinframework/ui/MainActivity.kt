package com.king.kotlinframework.ui

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.goat.kotlinbase.presenter.MainActivityPresenter
import com.goat.kotlinbase.presenter.impl.MainActivityPresenterImpl
import com.king.kotlinframework.adapter.GankIoAndroidAdapter
import com.king.kotlinframework.bean.GankIoDataBean
import com.king.kotlinframework.R
import kotterknife.bindView
/**
 * Created by wuxin on 2018/6/22.
 */
class MainActivity(override val layoutId: Int = R.layout.activity_main) : BaseActivity<MainActivityPresenterImpl>(), MainActivityPresenter.View, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private var page: Int = 0
    private val PRE_PAGE = 10
    val mRecyclerView: RecyclerView by bindView(R.id.recycler_view)
    val mRefresh: SwipeRefreshLayout by bindView(R.id.refresh)
    private var isRefresh = false
    val mAdapter: GankIoAndroidAdapter = GankIoAndroidAdapter(ArrayList())

    override fun refreshView(data: List<GankIoDataBean.ResultBean>) {
        if (isRefresh) {
            mRefresh!!.isRefreshing = false
            mAdapter.setEnableLoadMore(true)
            isRefresh = false
            mAdapter.setNewData(data)
        } else {
            mRefresh!!.isEnabled = true
            page++
            mAdapter.addData(data)
            mAdapter.loadMoreComplete()
        }
    }

    override fun initData() {
        mPresenter = MainActivityPresenterImpl()
    }

    override fun loadData() {
        mPresenter!!.fetchGankIoData(page, PRE_PAGE)
    }

    override fun initView() {
        mRecyclerView!!.layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.adapter = mAdapter
        mRefresh!!.setOnRefreshListener(this)
        mAdapter.setOnLoadMoreListener(this, mRecyclerView)
    }


    override fun onRefresh() {
        page = 0
        isRefresh = true
        mAdapter.setEnableLoadMore(false)
        mPresenter.fetchGankIoData(page, PRE_PAGE)
    }

    override fun onLoadMoreRequested() {
        if (page >= 6) {
            mAdapter.loadMoreEnd()
            mRefresh.isEnabled = true
        } else {
            loadData()
            mRefresh.isEnabled = false
        }
    }
}
