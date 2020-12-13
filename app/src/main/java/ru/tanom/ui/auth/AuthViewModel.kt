package ru.tanom.ui.auth

import androidx.lifecycle.MutableLiveData
import ru.tanom.base.viewmodel.BaseViewModel
import ru.tanom.base.viewmodel.Event
import ru.tanom.model.network.BaseResponse
import ru.tanom.model.network.dto.LoginRequest

class AuthViewModel : BaseViewModel() {
    val loginResult = MutableLiveData<Event<BaseResponse<String>>>()

    fun login(username: String, password: String) {
        request(loginResult) {
            apiService.login(LoginRequest(username, password))
        }
    }

}