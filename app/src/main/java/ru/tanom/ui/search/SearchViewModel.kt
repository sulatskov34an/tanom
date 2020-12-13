package ru.tanom.ui.search

import androidx.lifecycle.MutableLiveData
import ru.tanom.base.viewmodel.BaseViewModel
import ru.tanom.base.viewmodel.Event
import ru.tanom.model.network.BaseResponse
import ru.tanom.model.network.dto.Ad

class SearchViewModel : BaseViewModel() {
    val list = MutableLiveData<Event<BaseResponse<List<Ad>>>>()

    fun getAdsList() {
        request(list) { apiService.getAdsList() }
    }

}