package com.handarui.iqfun.util

import android.app.Activity
import android.util.Log
import java.util.*

/**
 * Created by gang on 2018/2/5 0005.
 */

/**
 * Activity管理类
 */
object AppManager {

    private val TAG = "AppManager"
    private var activityStack: Stack<Activity>? = null

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        Log.e(TAG, "addActivity = " + activity.javaClass.simpleName)
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 移除Activity
     */
    fun removeActivity(activity: Activity) {
        Log.e(TAG, "removeActivity() = " + activity.javaClass.simpleName)
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.remove(activity)
    }


    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity {
        return activityStack!!.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activity.finish()
            activityStack!!.remove(activity)
            activity = null
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        if (activityStack == null)
            return
        for (activity in activityStack!!) {
            if (activity.javaClass == cls) {
                finishActivity(activity)
                break
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                activityStack!![i].finish()
            }
            i++
        }
        activityStack!!.clear()
    }

    fun finishActivitiesExceptLogin() {
        activityStack?.forEach { it ->
//            if (it !is LoginActivity) {
//                it.finish()
//            }
        }
    }

    fun cantainActivity(activity: Activity): Boolean {
        return if (activityStack != null) {
            activityStack!!.contains(activity)
        } else false
    }

}