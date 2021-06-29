package com.bignerdranch.android.loginauthkotlin.data.network

import com.bignerdranch.android.loginauthkotlin.data.responses.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi : BaseApi {

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email")  email: String,
        @Field("password") password: String
    ): LoginResponse

}