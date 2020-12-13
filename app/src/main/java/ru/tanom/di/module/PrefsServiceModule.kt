package ru.tanom.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.tanom.model.prefs.PrefsService
import javax.inject.Singleton

@Module
class PrefsServiceModule {

    @Provides
    @Singleton
    fun providesPrefsService(context: Context): PrefsService {
        return PrefsService(context)
    }
}