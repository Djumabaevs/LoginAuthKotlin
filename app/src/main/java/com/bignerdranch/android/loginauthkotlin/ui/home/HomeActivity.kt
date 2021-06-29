package com.bignerdranch.android.loginauthkotlin.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.bignerdranch.android.loginauthkotlin.R
import com.bignerdranch.android.loginauthkotlin.data.UserPreferences
import com.bignerdranch.android.loginauthkotlin.ui.auth.AuthActivity
import com.bignerdranch.android.loginauthkotlin.ui.startNewActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var userPreferences: UserPreferences

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    fun performLogout() = lifecycleScope.launch {
        viewModel.logout()
        userPreferences.clear()
        startNewActivity(AuthActivity::class.java)
    }
}