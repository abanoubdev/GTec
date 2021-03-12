package com.softex.gtec.ui.forgetPassword

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.softex.gtec.R
import com.softex.gtec.databinding.FragmentForgetPasswordBinding
import com.softex.gtec.extensions.snackbarShort
import com.softex.gtec.model.ForgetPasswordResponse
import com.softex.gtec.ui.BaseFragment
import com.softex.gtec.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment(R.layout.fragment_forget_password) {

    private val viewModel: ForgetPasswordViewModel by viewModels()

    private var _binding: FragmentForgetPasswordBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForgetPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViews() {
        _binding?.ivBack?.setOnClickListener {
            Navigation.findNavController(
                requireActivity(),
                R.id.nav_host_fragment
            ).popBackStack()
        }
        _binding?.send?.setOnClickListener {
            viewModel.setStateEvent(ForgetPasswordStateEvent.ForgetPassword)
        }
        _binding?.email?.addTextChangedListener {
            viewModel.email = it.toString()
        }
    }

    override fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            if (dataState != null) {
                when (dataState) {
                    is DataState.Success<ForgetPasswordResponse?> -> {
                        displayLoading(false)
                        dataState.data?.let {
                            if (it.MailSent)
                                displaySuccess(getString(R.string.reset_password_message_success))
                            else
                                displayError(dataState.data.Message)
                        }
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
                is ForgetPasswordStateEvent.EmptyEmail -> {
                    _binding?.email?.error =
                        context?.getString(R.string.empty_email)
                }

                is ForgetPasswordStateEvent.InvalidEmail -> {
                    _binding?.email?.error =
                        context?.getString(R.string.invalid_email)
                }
                else -> {
                    Log.d("Exception", "No State")
                }
            }
        })
    }

    private fun displaySuccess(message: String) {
        snackbarShort(message)
    }

    private fun displayError(message: String?) {
        snackbarShort(message)
    }

    private fun displayLoading(isLoading: Boolean) {
        displayLoadingDialog(isLoading)
    }
}