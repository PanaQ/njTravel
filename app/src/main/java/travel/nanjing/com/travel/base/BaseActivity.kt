package com.handarui.iqfun.business.base

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import com.handarui.iqfun.util.AppManager
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import travel.nanjing.com.travel.R
import travel.nanjing.com.travel.util.CommonUtil

/**
 * Created by xubo on 2018/1/18.
 */
abstract class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (isLightStatusBar()) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            if (isFullScreen()) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                window.statusBarColor = Color.TRANSPARENT
            } else {
                window.statusBarColor = getStatusBarColor()
            }
        }
        AppManager.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.removeActivity(this)
    }

    open protected fun isFullScreen(): Boolean {
        return false
    }

    open protected fun getStatusBarColor(): Int {
        return CommonUtil.getColor(this, R.color.colorPrimary)
    }

    open protected fun isLightStatusBar(): Boolean {
        return false
    }

    open fun back(view: View) {
        finish()
    }


    fun showLoading() {

    }

    fun dismissLoading() {

    }

}