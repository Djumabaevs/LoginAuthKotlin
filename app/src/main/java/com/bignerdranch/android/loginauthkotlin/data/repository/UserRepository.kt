package com.bignerdranch.android.loginauthkotlin.data.repository

import com.bignerdranch.android.loginauthkotlin.data.UserPreferences
import com.bignerdranch.android.loginauthkotlin.data.network.AuthApi
import com.bignerdranch.android.loginauthkotlin.data.network.UserApi

class UserRepository(
    private val api: UserApi
): BaseRepository() {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}