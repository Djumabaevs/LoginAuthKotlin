package com.bignerdranch.android.loginauthkotlin.data.network

import com.bignerdranch.android.loginauthkotlin.data.responses.LoginResponse
import retrofit2.http.GET

interface UserApi {

    @GET("user")
    suspend fun getUser(): LoginResponse
}