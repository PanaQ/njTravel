package com.handarui.iqfun.business.base

import java.lang.ref.Reference
import java.lang.ref.SoftReference

/**
 * Created by xubo on 2018/1/19.
 */
abstract class BaseViewModel<T>(view: T){
    private var mViewRef: Reference<T> = SoftReference(view)

    fun detachView() {
        mViewRef.clear()
    }

    protected val view: T?
        get() = mViewRef.get()

}