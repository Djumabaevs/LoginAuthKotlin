package com.bignerdranch.android.loginauthkotlin.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bignerdranch.android.loginauthkotlin.R
import com.bignerdranch.android.loginauthkotlin.data.network.AuthApi
import com.bignerdranch.android.loginauthkotlin.data.network.Resource
import com.bignerdranch.android.loginauthkotlin.data.repository.AuthRepository
import com.bignerdranch.android.loginauthkotlin.databinding.FragmentLoginBinding
import com.bignerdranch.android.loginauthkotlin.ui.base.BaseFragment
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Success -> {
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                    lifecycleScope.launch {
                        userPreferences.saveAuthToken(it.value.user.access_token!!)

                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.buttonLogin.setOnClickListener {
            val email = binding.editTextTextEmailAddress.toString().trim()
            val password = binding.editTextTextPassword.toString().trim()
            //@todo add input validations
            viewModel.login(email, password)
        }
    }

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