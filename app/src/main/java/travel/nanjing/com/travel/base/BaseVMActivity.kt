package com.handarui.iqfun.business.base

import android.os.Bundle

/**
 * Created by xubo on 2018/1/22.
 */
abstract class BaseVMActivity<V, T : BaseViewModel<V>> : BaseActivity() {
    protected lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    abstract fun initViewModel()

    override fun onDestroy() {
        super.onDestroy()
        viewModel.detachView()
    }
}