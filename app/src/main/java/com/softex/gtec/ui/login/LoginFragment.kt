package com.softex.gtec.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.softex.gtec.R
import com.softex.gtec.databinding.LoginFragmentBinding
import com.softex.gtec.extensions.snackbarShort
import com.softex.gtec.model.User
import com.softex.gtec.ui.BaseFragment
import com.softex.gtec.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginFragment : BaseFragment(R.layout.login_fragment) {

    private val viewModel: LoginViewModel by viewModels()

    private var _binding: LoginFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViews() {
        _binding?.signIn?.setOnClickListener {
            viewModel.setStateEvent(loginStateEvent = LoginStateEvent.Login)
        }

        _binding?.tvForgetPassword?.setOnClickListener {

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
    }

    override fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<User> -> {
                    displayLoading(false)
                }

                is DataState.Error -> {
                    displayLoading(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayLoading(true)
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