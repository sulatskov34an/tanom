package ru.tanom.di.module

import dagger.Module
import dagger.Provides
import ru.tanom.ui.search.SearchViewModel

@Module
class ViewModelModule {

    @Provides
    fun provideSearchViewModel(): SearchViewModel {
        return SearchViewModel()
    }
}
