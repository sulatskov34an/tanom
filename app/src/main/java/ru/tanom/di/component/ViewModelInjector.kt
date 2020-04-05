package ru.tanom.di.component

import dagger.Component
import ru.tanom.di.module.NetworkModule
import ru.tanom.di.module.OkHttpClientModule
import ru.tanom.ui.home.HomeViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (OkHttpClientModule::class)])
interface ViewModelInjector {

    fun inject(homeViewModel: HomeViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}