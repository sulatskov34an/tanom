package ru.tanom.data.mvvm.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.tanom.data.mvvm.Event
import ru.tanom.data.network.Api
import ru.tanom.data.network.NetworkService

abstract class BaseViewModel : ViewModel() {

    var api: Api = NetworkService.retrofitService()


    fun <T> requestWithLiveData(
        liveData: MutableLiveData<Event<T>>,
        request: suspend () -> List<T>
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