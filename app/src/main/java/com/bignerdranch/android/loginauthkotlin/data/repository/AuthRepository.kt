package com.bignerdranch.android.loginauthkotlin.data.repository

import com.bignerdranch.android.loginauthkotlin.data.network.AuthApi

class AuthRepository(
    private val api: AuthApi
): BaseRepository() {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }

}