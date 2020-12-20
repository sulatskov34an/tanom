package ru.tanom.di.component

import dagger.Component
import ru.tanom.TanomApp
import ru.tanom.base.viewmodel.BaseViewModel
import ru.tanom.di.module.ContextModule
import ru.tanom.di.module.NetworkModule
import ru.tanom.di.module.OkHttpClientModule
import ru.tanom.di.module.PrefsServiceModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (OkHttpClientModule::class), (PrefsServiceModule::class), (ContextModule::class)])
interface MainComponent {

    fun inject(baseViewModel: BaseViewModel)
    fun inject(app: TanomApp)
}