package com.bignerdranch.android.loginauthkotlin.ui.auth

import androidx.lifecycle.ViewModel
import com.bignerdranch.android.loginauthkotlin.data.repository.AuthRepository

class AuthViewModel(
    private val repository: AuthRepository
): ViewModel() {
}