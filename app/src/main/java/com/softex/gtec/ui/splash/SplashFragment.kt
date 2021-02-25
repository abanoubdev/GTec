package com.softex.gtec.ui.splash

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import com.softex.gtec.R
import com.softex.gtec.extensions.snackbarShort
import com.softex.gtec.extensions.startHomepage
import com.softex.gtec.model.User
import com.softex.gtec.ui.BaseFragment
import com.softex.gtec.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.Exception

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class SplashFragment : BaseFragment(R.layout.fragment_splash) {

    private val viewModel: SplashViewModel by viewModels()

    override fun initViews() {
        viewModel.setStateEvent(SplashStateEvent.GetUser)
    }

    override fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<User?> -> {
                    Handler(Looper.getMainLooper()).postDelayed({
                        activity?.let {
                            if (dataState.data != null) {
                                startHomepage()
                            } else {
                                findNavController(
                                    requireActivity(),
                                    R.id.nav_host_fragment
                                ).navigate(R.id.action_splashFragment_to_loginFragment)
                            }
                        }
                    }, 2000)
                }

                is DataState.Error -> {
                    displayError(dataState.exception)
                }
            }
        })
    }

    private fun displayError(exception: Exception) {
        snackbarShort(exception)
    }
}