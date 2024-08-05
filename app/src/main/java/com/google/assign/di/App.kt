package com.google.assign.di

import android.app.Application
import com.google.assign.di.NetworkModule.networkModule
import com.google.assign.di.RepositoryModule.repositoryModule
import com.google.assign.di.ServiceModule.serviceModule
import com.google.assign.di.ViewModelModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class App : Application() {

    private val modules = arrayListOf(
        networkModule,
        repositoryModule,
        serviceModule,
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