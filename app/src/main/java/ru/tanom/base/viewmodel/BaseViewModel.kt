package ru.tanom.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.tanom.di.component.DaggerMainComponent
import ru.tanom.model.network.ApiInterface
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    lateinit var api: ApiInterface

    init {
        DaggerMainComponent.builder().build().inject(this)
    }

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