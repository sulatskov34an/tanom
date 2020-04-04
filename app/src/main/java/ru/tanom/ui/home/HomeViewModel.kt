package ru.tanom.ui.home

import androidx.lifecycle.MutableLiveData
import ru.tanom.model.network.dto.Ads
import ru.tanom.base.viewmodel.Event
import ru.tanom.base.viewmodel.BaseViewModel

class HomeViewModel : BaseViewModel() {
    val list = MutableLiveData<Event<List<Ads>>>()

    fun getAdsList() {
        request(list) {api.getAdsList()}
    }
}