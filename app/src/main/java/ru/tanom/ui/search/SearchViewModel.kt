package ru.tanom.ui.search

import androidx.lifecycle.MutableLiveData
import ru.tanom.model.network.dto.Ad
import ru.tanom.base.viewmodel.Event
import ru.tanom.base.viewmodel.BaseViewModel

class SearchViewModel : BaseViewModel() {
    val list = MutableLiveData<Event<List<Ad>>>()

    fun getAdsList() {
        request(list) {api.getAdsList()}
    }
}