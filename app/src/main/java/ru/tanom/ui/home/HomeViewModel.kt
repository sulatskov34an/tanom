package ru.tanom.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.tanom.data.model.Ads
import ru.tanom.data.mvvm.Event
import ru.tanom.data.mvvm.viewModel.BaseViewModel

class HomeViewModel : BaseViewModel() {
    val simpleLiveData = MutableLiveData<Event<Ads>>()

    fun getAdsList() {
        requestWithLiveData(simpleLiveData, {api.getAdsList()})
    }

/*
    fun getAdsOne(id: Int, callback: (data: Event<Ads>) -> Unit) {
        requestWithLiveData(simpleLiveData, {api.getAdsOne(id = id)})
    }
*/
}