package com.handarui.iqfun.util

import travel.nanjing.com.travel.MyApplication
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

    var isInvited: kotlin.Int
        get() {
            return SPUtils.getInt(MyApplication.instance, "isInvited")
        }
        set(value) {
            SPUtils.putInt(MyApplication.instance, "isInvited", value)
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

    var totalEarning: kotlin.Float?
        get() {
            return SPUtils.getFloat(MyApplication.instance, "totalEarning")
        }
        set(value) {
            SPUtils.putFloat(MyApplication.instance, "totalEarning", value!!)
        }
    var ranking: kotlin.String?
        get() {
            return SPUtils.getString(MyApplication.instance, "ranking")
        }
        set(value) {
            SPUtils.putString(MyApplication.instance, "ranking", value!!)
        }


//    public fun getIsUserHasLogin(): Boolean {
//        return !TextUtils.isEmpty(TokenManager.getToken(MyApplication.instance))
//    }

//    public fun saveUserInfo(userInfo: UserBean) {
//        balance = userInfo.balance
//        id = userInfo.id
//        inviteCode = userInfo.inviteCode
//        isInvited = if (userInfo.isInvited == null || userInfo.isInvited == 0) 0 else 1
//        name = userInfo.name
//        portraitUrl = userInfo.portraitUrl
//        revivalCardNum = userInfo.revivalCardNum
//        ranking = if (userInfo.ranking == null || userInfo.ranking == -1) "--" else userInfo.ranking.toString()
//    }
//
//    fun relogin(context: Context) {
//        LoginManager.getInstance().logOut()
//        TokenManager.removeToken(context)
//        context.startActivity(Intent(context, LoginActivity::class.java))
//        AppManager.finishActivitiesExceptLogin()
//    }
}