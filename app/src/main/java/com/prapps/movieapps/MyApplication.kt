package com.prapps.movieapps

import android.app.Application
import com.prapps.core.core.di.databaseModule

import com.prapps.core.core.di.networkModule
import com.prapps.core.core.di.repositoryModule
import com.prapps.movieapps.di.useCaseModule
import com.prapps.movieapps.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}