package com.bignerdranch.android.loginauthkotlin.data.responses


data class TokenResponse(
    val access_token: String?,
    val refresh_token: String?
)