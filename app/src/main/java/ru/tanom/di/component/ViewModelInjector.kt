package ru.tanom.di.component

import dagger.Component
import ru.tanom.di.module.NetworkModule
import ru.tanom.di.module.OkHttpClientModule
import ru.tanom.ui.ad_details.AdDetailsViewModel
import ru.tanom.ui.search.SearchViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetworkModule::class), (OkHttpClientModule::class)])
interface ViewModelInjector {

    fun inject(searchViewModel: SearchViewModel)
    fun inject(adDetailsViewModel: AdDetailsViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}