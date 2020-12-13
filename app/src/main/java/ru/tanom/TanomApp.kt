package ru.tanom

import android.app.Application
import ru.tanom.di.component.DaggerMainComponent


class TanomApp: Application() {
    override fun onCreate() {
        DaggerMainComponent.builder().build().inject(this)
        super.onCreate()
    }
}
