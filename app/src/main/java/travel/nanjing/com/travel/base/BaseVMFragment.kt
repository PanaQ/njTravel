package com.handarui.iqfun.business.base

import android.os.Bundle

/**
 * Created by xubo on 2018/1/22.
 */
abstract class BaseVMFragment<V, T : BaseViewModel<V>> : BaseFragment() {
    protected lateinit var viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.detachView()
    }

    abstract fun initViewModel()
}