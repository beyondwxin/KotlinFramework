package com.king.kotlinframework.bean

import java.io.Serializable

data class ResultBaseData<out T> constructor(val isError: Boolean = false, val results: T? = null) : Serializable