package com.bignerdranch.android.loginauthkotlin.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bignerdranch.android.loginauthkotlin.data.network.Resource
import com.bignerdranch.android.loginauthkotlin.data.repository.UserRepository
import com.bignerdranch.android.loginauthkotlin.data.responses.LoginResponse
import com.bignerdranch.android.loginauthkotlin.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val repository: UserRepository
): BaseViewModel(repository) {

    private val _user: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val user: LiveData<Resource<LoginResponse>>
    get() = _user

    fun getUser() = viewModelScope.launch {
        _user.value = Resource.Loading
        _user.value = repository.getUser()

    }

}