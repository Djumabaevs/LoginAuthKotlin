package com.bignerdranch.android.loginauthkotlin.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bignerdranch.android.loginauthkotlin.R
import com.bignerdranch.android.loginauthkotlin.data.network.AuthApi
import com.bignerdranch.android.loginauthkotlin.data.network.Resource
import com.bignerdranch.android.loginauthkotlin.data.repository.AuthRepository
import com.bignerdranch.android.loginauthkotlin.databinding.FragmentLoginBinding
import com.bignerdranch.android.loginauthkotlin.ui.base.BaseFragment
import com.bignerdranch.android.loginauthkotlin.ui.enable
import com.bignerdranch.android.loginauthkotlin.ui.handleApiError
import com.bignerdranch.android.loginauthkotlin.ui.home.HomeActivity
import com.bignerdranch.android.loginauthkotlin.ui.startNewActivity
import com.bignerdranch.android.loginauthkotlin.ui.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch



@AndroidEntryPoint
class LoginFragment: Fragment(R.layout.fragment_login) {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModels<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        binding.progressbar.visible(false)
        binding.buttonLogin.enable(false)

        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAccessTokens(
                            it.value.user.access_token!!,
                            it.value.user.refresh_token!!
                        )
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }
                is Resource.Failure -> handleApiError(it) { login() }
            }
        })

        binding.editTextTextPassword.addTextChangedListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            binding.buttonLogin.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }

        binding.buttonLogin.setOnClickListener {
            login()
        }
    }

    private fun login() {
        val email = binding.editTextTextEmailAddress.text.toString().trim()
        val password = binding.editTextTextPassword.text.toString().trim()
        viewModel.login(email, password)
    }
}

//class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {
//
//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        binding.progressbar.visible(false)
//        binding.buttonLogin.enable(false)
//
//        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
//            binding.progressbar.visible(it is Resource.Loading)
//            when (it) {
//                is Resource.Success -> {
//                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
//
//                      lifecycleScope.launch {
//                          viewModel.saveAuthToken(it.value.user.access_token!!)
//                          requireActivity().startNewActivity(HomeActivity::class.java)
//                      }
//
//                }
//                is Resource.Failure -> {
//                    handleApiError(it) { login() }
//                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
//                }
//            }
//        })
//
//        binding.editTextTextPassword.addTextChangedListener {
//            val email = binding.editTextTextEmailAddress.toString().trim()
//            binding.buttonLogin.enable(email.toString().isNotEmpty() && it.toString().isNotEmpty())
//        }
//
//        binding.buttonLogin.setOnClickListener {
//            login()
//        }
//    }
//
//    private fun login() {
//        val email = binding.editTextTextEmailAddress.toString().trim()
//        val password = binding.editTextTextPassword.toString().trim()
//        viewModel.login(email, password)
//    }
//
//    override fun getViewModel() = AuthViewModel::class.java
//
//    override fun getFragmentBinding(
//        inflater: LayoutInflater,
//        container: ViewGroup?
//    ) = FragmentLoginBinding.inflate(inflater, container, false)
//
//    override fun getFragmentRepository() =
//        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


    /* override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         return inflater.inflate(R.layout.fragment_login, container, false)
     }*/

