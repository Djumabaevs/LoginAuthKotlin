package com.bignerdranch.android.loginauthkotlin.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.loginauthkotlin.R
import com.bignerdranch.android.loginauthkotlin.data.network.AuthApi
import com.bignerdranch.android.loginauthkotlin.data.repository.AuthRepository
import com.bignerdranch.android.loginauthkotlin.databinding.FragmentLoginBinding
import com.bignerdranch.android.loginauthkotlin.ui.base.BaseFragment

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


    /* override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         return inflater.inflate(R.layout.fragment_login, container, false)
     }*/

}