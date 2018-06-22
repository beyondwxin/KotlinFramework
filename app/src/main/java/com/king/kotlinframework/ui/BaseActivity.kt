package com.king.kotlinframework.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.king.kotlinframework.http.LifeSubscription
import com.king.kotlinframework.http.Stateful
import com.king.kotlinframework.widgets.LoadingPage
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import shinetechzz.com.vcleaders.presenter.base.BasePresenter
/**
 * Created by wuxin on 2018/6/22.
 */
abstract class BaseActivity<T : BasePresenter<*>> : AppCompatActivity(), LifeSubscription, Stateful {
    val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
    lateinit var mPresenter: T
    var mLoadingPage: LoadingPage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        mPresenter!!.attachView(this)
        mLoadingPage = object : LoadingPage(this) {
            override fun initView() {
                this@BaseActivity.initView()
            }

            override fun loadData() {
                this@BaseActivity.loadData()
            }

            override fun getLayoutId(): Int {
                return this@BaseActivity.layoutId
            }
        }
        setContentView(mLoadingPage)
        loadData()
    }

    //用于监听rxJava防止内存泄漏
    override fun bindSubscription(disposable: Disposable) {
        mCompositeDisposable!!.add((disposable))
    }

    override fun setState(state: Int) {
        mLoadingPage!!.state = state
        mLoadingPage!!.showPage()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
        mPresenter!!.detachView()
    }

    /**
     * 初始化数据接收intent的数据
     */
    abstract fun initData()

    /**
     * 请求网络获取的数据返回状态
     * 如果是静态页面不需要网络请求的，在子类的loadData方法中添加以下2行即可
     * mLoadingPage.state = STATE_SUCCESS
     * mLoadingpage.showPage()
     * 或者调用setState(AppConstants.STATE_SUCCESS)
     */
    abstract fun loadData()

    /**
     * 请求网络成功再去加载布局
     */
    abstract val layoutId: Int

    /**
     * 子类关于View操作（如setAdapter）都必须放在这里面，会因为页面状态不成功，而binding创建就引用而导致空指针
     * loadData()和initView只执行一次，如果有一些请求需要二次的不要放到loadData()里面。
     */
    abstract fun initView()
}
