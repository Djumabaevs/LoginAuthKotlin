package com.bignerdranch.android.loginauthkotlin.ui.base

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.loginauthkotlin.data.network.UserApi
import com.bignerdranch.android.loginauthkotlin.data.repository.BaseRepository

abstract class BaseViewModel(
    private val repository: BaseRepository
): ViewModel() {

    suspend fun logout(api: UserApi) = repository.logout(api)
}