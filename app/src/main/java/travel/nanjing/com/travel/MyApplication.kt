/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package travel.nanjing.com.travel

import android.app.Application
import android.content.Context
import com.handarui.baselib.AndroidBase
import com.handarui.baselib.net.RetrofitFactory
import travel.nanjing.com.travel.util.Constant


/**
 * Android Main Application
 */
class MyApplication : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        initAndroidBase()
    }

    fun initAndroidBase() {
        val httpPath = Constant.SERVER_ADDRESS
        val androidBaseBuilder = AndroidBase.AndroidBaseBuilder(this, httpPath, httpPath)
        androidBaseBuilder.setAESToken("Kocakin@20161001")
        androidBaseBuilder.setDebug(true)
        androidBaseBuilder.createAndroidBase().init()

        androidBaseBuilder.createAndroidBase().init()
        RetrofitFactory.init(this)
    }

    companion object {
        lateinit var instance: MyApplication
    }

}

