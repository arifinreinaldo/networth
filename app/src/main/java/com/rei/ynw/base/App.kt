package com.rei.ynw.base

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.rei.ynw.data.Repository


class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
//        if (BuildConfig.DEBUG) {
//            Stetho.initializeWithDefaults(this)
//
//        } else {
//
//        }
//        LocalValue.init(this)
        Repository.init(this)
    }

    companion object {
        val context: Context
            get() = instance?.applicationContext as Context

        @get:Synchronized
        var instance: App? = null
            private set
    }
}