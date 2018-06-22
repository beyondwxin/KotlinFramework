package com.king.kotlinframework

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
/**
 * Created by wuxin on 2018/6/22.
 */
class GankIoAndroidAdapter(data: List<GankIoDataBean.ResultBean>) : BaseQuickAdapter<GankIoDataBean.ResultBean, BaseViewHolder>(R.layout.item_wechat, data) {
    override fun convert(helper: BaseViewHolder, item: GankIoDataBean.ResultBean) {
        val ivAndroidPic = helper.getView<ImageView>(R.id.iv_android_pic)
        if (item.images != null && item.images.isNotEmpty() && !TextUtils.isEmpty(item.images[0])) {
            ivAndroidPic.visibility = View.VISIBLE
            Glide.with(ivAndroidPic.context).load(item.images[0]).crossFade(500).into(ivAndroidPic)
        } else {
            ivAndroidPic.visibility = View.GONE
        }
        helper.setText(R.id.tv_android_des, item.desc)
        helper.setText(R.id.tv_android_who, item.who)
        helper.setText(R.id.tv_android_time, item.publishedAt)
    }
}