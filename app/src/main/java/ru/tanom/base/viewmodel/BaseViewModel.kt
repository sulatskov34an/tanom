package ru.tanom.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.tanom.di.component.DaggerViewModelInjector
import ru.tanom.di.component.ViewModelInjector
import ru.tanom.di.module.NetworkModule
import ru.tanom.model.network.ApiInterface
import ru.tanom.ui.search.SearchViewModel
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is SearchViewModel -> injector.inject(this)

        }
    }

    @Inject
    lateinit var api: ApiInterface

    fun <T> request(
        liveData: MutableLiveData<Event<T>>,
        request: suspend () -> T
    ) {

        liveData.postValue(Event.loading())

        this.viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = request.invoke()
                if (response != null) {
                    liveData.postValue(Event.success(response))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                liveData.postValue(Event.error(null))
            }
        }
    }
}