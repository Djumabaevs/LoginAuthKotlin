package com.bignerdranch.android.loginauthkotlin.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.loginauthkotlin.data.repository.AuthRepository
import com.bignerdranch.android.loginauthkotlin.data.repository.BaseRepository
import com.bignerdranch.android.loginauthkotlin.data.repository.UserRepository
import com.bignerdranch.android.loginauthkotlin.ui.auth.AuthViewModel
import com.bignerdranch.android.loginauthkotlin.ui.home.HomeViewModel
import java.lang.IllegalArgumentException

//class ViewModelFactory(
//    private val repository: BaseRepository
//): ViewModelProvider.NewInstanceFactory() {
//
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//            return  when{
//                modelClass.isAssignableFrom(AuthViewModel::class.java) ->
//                    AuthViewModel(repository as AuthRepository) as T
//
//                modelClass.isAssignableFrom(HomeViewModel::class.java) ->
//                    HomeViewModel(repository as UserRepository) as T
//            else -> throw IllegalArgumentException("ViewModel class not found")
//        }
//    }
//
//}