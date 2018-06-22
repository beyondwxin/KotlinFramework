package com.king.kotlinframework

import java.io.Serializable
/**
 * Created by wuxin on 2018/6/22. 
 */
data class GankIoDataBean constructor(val isError: Boolean = false, val results: List<ResultBean>? = null) : Serializable {
    data class ResultBean(val _id: String? = null,
                          val createdAt: String? = null,
                          val desc: String? = null,
                          val publishedAt: String? = null,
                          val source: String? = null,
                          val type: String? = null,
                          val url: String? = null,
                          val isUsed: Boolean = false,
                          val who: String? = null,
                          val images: List<String>? = null) : Serializable {

    }
}