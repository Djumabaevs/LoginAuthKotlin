package com.bignerdranch.android.loginauthkotlin.data.repository

import com.bignerdranch.android.loginauthkotlin.data.UserPreferences
import com.bignerdranch.android.loginauthkotlin.data.network.AuthApi
import com.bignerdranch.android.loginauthkotlin.data.network.UserApi
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserApi
): BaseRepository(api) {

    suspend fun getUser() = safeApiCall {
        api.getUser()
    }

}