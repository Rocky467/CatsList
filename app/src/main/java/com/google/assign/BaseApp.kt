package com.google.assign

import android.app.Application
import com.google.assign.adapter.PagingAdapter
import com.google.assign.api.ApiService
import com.google.assign.viewModel.ListViewModel
import com.google.assign.viewModel.ListViewModelFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApp)
            modules(listOf(appModule))
        }
    }

    private val appModule = module {
        single { ApiService }
        single { ListViewModelFactory(get()) }
        single { ListViewModel(get()) }
        single { PagingAdapter(get()) }
    }

}
