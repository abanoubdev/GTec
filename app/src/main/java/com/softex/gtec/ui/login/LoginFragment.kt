package com.softex.gtec.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.softex.gtec.R
import com.softex.gtec.model.User
import com.softex.gtec.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class LoginFragment : Fragment() {

    companion object {
        fun newInstance() = LoginFragment()
    }

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        viewModel.setStateEvent(loginStateEvent = LoginStateEvent.Login)
    }

    private fun subscribeObservers() {
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

    }

    private fun displayLoading(isLoading: Boolean) {
    }
}