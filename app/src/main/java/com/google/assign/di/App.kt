package com.google.assign.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class App : Application() {

    private val modules = arrayListOf(
        networkModule,
        serviceModule,
        dataSourceModule,
        repositoryModule,
        viewModelModule
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            loadKoinModules(modules)
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}