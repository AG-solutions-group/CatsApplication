package com.example.catsapplication.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class KoinStart : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.NONE)
            androidContext(this@KoinStart)
            modules(listOf(appModule, domainModule, repositoryModule))
        }
    }
}