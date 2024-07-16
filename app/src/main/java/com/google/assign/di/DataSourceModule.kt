package com.google.assign.di

import com.google.assign.network.RemoteDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    single {  RemoteDataSource(get()) }
}