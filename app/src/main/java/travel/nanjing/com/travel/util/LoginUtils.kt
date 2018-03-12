package com.handarui.iqfun.util

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Log
import com.handarui.baselib.net.TokenManager
import travel.nanjing.com.travel.MyApplication
import travel.nanjing.com.travel.R
import travel.nanjing.com.travel.business.api.model.bo.UserBo
import travel.nanjing.com.travel.business.login.LoginActivity
import travel.nanjing.com.travel.util.Constant
import travel.nanjing.com.travel.util.SPUtils

/**
 * Created by zx on 2018/1/30 0030.
 */
object LoginUtils {
    var balance: kotlin.Double?
        get() {
            return SPUtils.getFloat(MyApplication.instance, "banlance").toDouble()
        }
        set(value) {
            SPUtils.putFloat(MyApplication.instance, "banlance", value!!.toFloat())
        }

    var id: kotlin.Long
        get() {
            return SPUtils.getLongPreferences(MyApplication.instance, "id")
        }
        set(value) {
            SPUtils.putLong(MyApplication.instance, "id", value)
        }

    var inviteCode: kotlin.String?
        get() {
            return SPUtils.getString(MyApplication.instance, "inviteCode")
        }
        set(value) {
            SPUtils.putString(MyApplication.instance, "inviteCode", value)
        }

    var sex: kotlin.Int
        get() {
            return SPUtils.getInt(MyApplication.instance, "sex")
        }
        set(value) {
            SPUtils.putInt(MyApplication.instance, "sex", value)
        }

    var name: kotlin.String?
        get() {
            return SPUtils.getString(MyApplication.instance, "name")
        }
        set(value) {
            SPUtils.putString(MyApplication.instance, "name", value)
        }

    var portraitUrl: kotlin.String?
        get() {
            return SPUtils.getString(MyApplication.instance, "portraitUrl")
        }
        set(value) {
            SPUtils.putString(MyApplication.instance, "portraitUrl", value)
        }

    var revivalCardNum: kotlin.Long
        get() {
            return SPUtils.getLongPreferences(MyApplication.instance, "revivalCardNum")
        }
        set(value) {
            SPUtils.putLong(MyApplication.instance, "revivalCardNum", value)
        }

    var email: kotlin.String?
        get() {
            return SPUtils.getString(MyApplication.instance, "email")
        }
        set(value) {
            SPUtils.putString(MyApplication.instance, "email", value!!)
        }
    var phoneNum: kotlin.String?
        get() {
            return SPUtils.getString(MyApplication.instance, "phoneNum")
        }
        set(value) {
            SPUtils.putString(MyApplication.instance, "phoneNum", value!!)
        }


    public fun getIsUserHasLogin(): Boolean {
        return !TextUtils.isEmpty(TokenManager.getToken(MyApplication.instance))
    }

    public fun saveUserInfo(userInfo: UserBo) {
        id = userInfo.id
        name = userInfo.name
        val replace = userInfo.avatar.replace("http://localhost:8080", Constant.SERVER_ADDRESS)
        Log.i("asasa",replace);
        portraitUrl = replace
        phoneNum = userInfo.phone
        sex = userInfo.gender
        email = userInfo.email
    }

    fun relogin(context: Context) {
        TokenManager.removeToken(context)
        context.startActivity(Intent(context, LoginActivity::class.java))
        AppManager.finishActivitiesExceptLogin()
    }

    fun getAva(): String {
        var stringArray = MyApplication.instance.resources.getStringArray(R.array.avaPic)
        return stringArray[(Math.random() * 5).toInt()]
    }
}