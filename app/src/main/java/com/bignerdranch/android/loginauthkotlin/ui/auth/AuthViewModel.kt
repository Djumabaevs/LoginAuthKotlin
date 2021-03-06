package com.bignerdranch.android.loginauthkotlin.ui.auth

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.loginauthkotlin.data.network.Resource
import com.bignerdranch.android.loginauthkotlin.data.repository.AuthRepository
import com.bignerdranch.android.loginauthkotlin.data.responses.LoginResponse
import com.bignerdranch.android.loginauthkotlin.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class AuthViewModel @ViewModelInject constructor(
    private val repository: AuthRepository
): BaseViewModel(repository) {

    private val _loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val loginResponse: LiveData<Resource<LoginResponse>>
    get() = _loginResponse

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch {
        _loginResponse.value = Resource.Loading
        _loginResponse.value = repository.login(email, password)
    }

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        repository.saveAccessTokens(accessToken, refreshToken)
    }

//     suspend fun saveAuthToken(token: String) {
//        repository.saveAuthToken(token)
//    }
}