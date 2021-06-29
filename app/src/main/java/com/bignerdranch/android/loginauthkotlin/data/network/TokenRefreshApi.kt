package com.bignerdranch.android.loginauthkotlin.data.network

import com.bignerdranch.android.loginauthkotlin.data.responses.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface TokenRefreshApi : BaseApi {
    @FormUrlEncoded
    @POST("auth/refresh-token")
    suspend fun refreshAccessToken(
        @Field("refresh_token") refreshToken: String?
    ): TokenResponse
}