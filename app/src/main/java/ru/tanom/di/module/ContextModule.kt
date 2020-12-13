package ru.tanom.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val application: Application) {

    @Provides
    internal fun providesContext(): Context {
        return application.applicationContext
    }
}