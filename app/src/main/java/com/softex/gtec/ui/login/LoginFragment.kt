package com.softex.gtec.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.softex.gtec.R
import com.softex.gtec.databinding.FragmentLoginBinding
import com.softex.gtec.extensions.snackbarShort
import com.softex.gtec.model.User
import com.softex.gtec.ui.BaseFragment
import com.softex.gtec.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.Exception

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModels()

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViews() {
        _binding?.signIn?.setOnClickListener {
            viewModel.setStateEvent(loginStateEvent = LoginStateEvent.Login)
        }

        _binding?.tvForgetPassword?.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
            ).navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
        }

        _binding?.signUp1?.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
            ).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        _binding?.signUp2?.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
            ).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        _binding?.etUsernameOrEmail?.addTextChangedListener {
            viewModel.username = it.toString()
        }

        _binding?.etPassword?.addTextChangedListener {
            viewModel.password = it.toString()
        }
    }

    override fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            if (dataState != null) {
                when (dataState) {
                    is DataState.Success<User?> -> {
                        displayLoading(false)
                        Navigation.findNavController(
                            requireActivity(),
                            R.id.nav_host_fragment
                        ).navigate(R.id.action_loginFragment_to_homepageFragment)
                    }

                    is DataState.Error -> {
                        displayLoading(false)
                        displayError(dataState.exception.message)
                    }

                    is DataState.Loading -> {
                        displayLoading(true)
                    }
                }
            }
        })

        viewModel.errorState.observe(viewLifecycleOwner, {
            when (it) {
                is LoginStateEvent.InvalidUsername -> {
                    _binding?.etUsernameOrEmail?.error =
                        context?.getString(R.string.invalid_username_or_email)
                }

                is LoginStateEvent.InvalidPassword -> {
                    _binding?.etPassword?.error =
                        context?.getString(R.string.invalid_password)
                }
                else -> {
                    throw Exception("State not found")
                }
            }
        })
    }

    private fun displayError(message: String?) {
        snackbarShort(message)
    }

    private fun displayLoading(isLoading: Boolean) {
        displayLoadingDialog(isLoading)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}