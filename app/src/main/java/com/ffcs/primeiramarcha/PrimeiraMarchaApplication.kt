package com.ffcs.primeiramarcha

import android.app.Application
import com.ffcs.primeiramarcha.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PrimeiraMarchaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PrimeiraMarchaApplication)
            modules(appModules)
        }
    }
}