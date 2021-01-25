package com.softex.gtec.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.softex.gtec.BuildConfig
import com.softex.gtec.model.Blog
import com.softex.gtec.util.DataState
import com.softex.gtec.R
import com.softex.gtec.extensions.log
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val _tagClass: String = "MainFragment"

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()

        viewModel.setStateEvent(mainStateEvent = MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                }

                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }

                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
//        if (isDisplayed)
//            progress.visibility = View.VISIBLE
//        else
//            progress.visibility = View.GONE

        log(_tagClass, isDisplayed.toString())
    }

    private fun displayError(message: String?) {
        log(_tagClass, message!!)
        log(_tagClass, BuildConfig.encrypted_ex_app_id)
    }
}