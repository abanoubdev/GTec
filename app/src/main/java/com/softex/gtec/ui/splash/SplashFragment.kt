package com.softex.gtec.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.softex.gtec.R
import com.softex.gtec.model.User
import com.softex.gtec.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SplashFragment : Fragment(R.layout.splash_fragment) {

    private val viewModel: SplashViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()

        viewModel.setStateEvent(SplashStateEvent.GetUser)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<User?> -> {
                    Handler().postDelayed({
                        displayLoading(false)
                        if (dataState.data != null) {
                            findNavController(
                                requireActivity(),
                                R.id.nav_host_fragment
                            ).navigate(R.id.action_splashFragment_to_mainFragment)
                        } else {
                            findNavController(
                                requireActivity(),
                                R.id.nav_host_fragment
                            ).navigate(R.id.action_splashFragment_to_loginFragment)
                        }
                    }, 2000)
                }

                is DataState.Error -> {
                    displayLoading(false)
                }

                is DataState.Loading -> {
                    displayLoading(true)
                }
            }
        })
    }

    private fun displayError(message: String?) {
    }

    private fun displayLoading(isLoading: Boolean) {
    }
}