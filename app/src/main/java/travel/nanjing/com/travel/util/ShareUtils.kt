package com.handarui.iqfun.util

import android.content.Intent


/**
 * Created by zx on 2018/2/5 0005.
 */
object ShareUtils {
    fun shareLink(link: String){
        val sharingIntent = Intent(android.content.Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "")
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, link)
//        context.startActivity(Intent.createChooser(sharingIntent, context.resources.getString(R.string.app_name)))
    }
}