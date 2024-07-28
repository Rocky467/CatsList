package com.google.assign.di

import com.google.assign.network.UserPagingSource
import org.koin.dsl.module

val dataSourceModule = module {
    single {  UserPagingSource(get()) }
}