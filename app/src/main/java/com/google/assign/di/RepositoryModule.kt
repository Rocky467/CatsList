package com.google.assign.di

import com.google.assign.network.Repository
import org.koin.dsl.module

object RepositoryModule {

    val repositoryModule = module {
        single { Repository(get()) }
    }
}