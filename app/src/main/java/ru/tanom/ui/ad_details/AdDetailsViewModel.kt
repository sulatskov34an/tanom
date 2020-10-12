package ru.tanom.ui.ad_details

import androidx.lifecycle.MutableLiveData
import ru.tanom.base.viewmodel.BaseViewModel
import ru.tanom.base.viewmodel.Event
import ru.tanom.model.network.BaseResponse
import ru.tanom.model.network.dto.String

class AdDetailsViewModel : BaseViewModel() {

    val ad = MutableLiveData<Event<BaseResponse<String>>>()

    fun getAd(id: Int?) {
        request(ad) {api.getAd(id)}
    }
}