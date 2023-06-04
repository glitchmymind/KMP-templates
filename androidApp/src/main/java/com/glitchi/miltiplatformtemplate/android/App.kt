package com.glitchi.miltiplatformtemplate.android

import android.app.Application
import config.PlatformConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initPlatformSDK()
    }
}

fun App.initPlatformSDK() =
    PlatformSDK.init(
        configuration = PlatformConfig(context = applicationContext)
    )