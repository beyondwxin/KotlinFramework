package shinetechzz.com.vcleaders.presenter.base

import com.goat.kotlinbase.presenter.base.BaseView
import com.king.kotlinframework.http.LifeSubscription
import com.king.kotlinframework.http.utils.Callback
import com.king.kotlinframework.http.utils.HttpUtils
import io.reactivex.Observable

/**
 * Created by wuxin on 2018/6/22.
 */
open class BasePresenter<T : BaseView<*>> {
    protected var mView: T? = null//指的是界面，也就是BaseFragment或者BaseActivity

    private var callback: Callback<*>? = null

    fun attachView(mLifeSubscription: LifeSubscription) {
        this.mView = mLifeSubscription as T
    }

    fun <T> invoke(observable: Observable<T>, callback: Callback<T>) {
        this.callback = callback
        HttpUtils.invoke(mView as LifeSubscription, observable, callback)
    }

    fun detachView() {
        if (mView != null)
            mView = null
        if (callback != null) {
            callback!!.detachView()
        }

    }
}
