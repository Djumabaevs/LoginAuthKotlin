package com.bignerdranch.android.loginauthkotlin.data.repository

import com.bignerdranch.android.loginauthkotlin.data.UserPreferences
import com.bignerdranch.android.loginauthkotlin.data.network.AuthApi
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: AuthApi,
    private val preferences: UserPreferences
): BaseRepository(api) {

    suspend fun login(
        email: String,
        password: String
    ) = safeApiCall {
        api.login(email, password)
    }


    suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        preferences.saveAccessTokens(accessToken, refreshToken)
    }

//    suspend fun saveAuthToken(token: String) {
//        preferences.saveAuthToken(token)
//    }

}