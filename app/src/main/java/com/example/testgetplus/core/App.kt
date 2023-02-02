package com.example.testgetplus.core

import android.app.Application
import com.example.testgetplus.di.networkModule
import com.example.testgetplus.di.repositoryModule
import com.example.testgetplus.di.useCaseModule
import com.example.testgetplus.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}