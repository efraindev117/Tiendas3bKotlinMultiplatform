package com.tienda3b.app

import android.app.Application
import com.tienda3b.app.di.initKoinAndroid
import org.koin.android.ext.koin.androidContext

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid {
            androidContext(this@AndroidApp)
        }
    }
}