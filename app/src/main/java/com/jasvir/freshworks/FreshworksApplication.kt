package com.jasvir.freshworks

import android.app.Application
import com.jasvir.freshworks.di.networkModule
import com.jasvir.freshworks.di.repositoryModule
import com.jasvir.freshworks.di.roomModule
import com.jasvir.freshworks.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class
 */
class FreshworksApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * DI initialization
         */
        startKoin {
            printLogger() // Koin Logger
            androidContext(this@FreshworksApplication)
            modules(listOf(roomModule, viewModelModule, networkModule, repositoryModule))
        }
    }
}