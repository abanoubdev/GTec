package com.softex.gtec.ui


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.softex.gtec.model.Blog
import com.softex.gtec.util.DataState
import com.softex.gtec.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment
constructor(
    private val someString: String
) : Fragment(R.layout.fragment_main) {

    private val TAG: String = "MainFragment"

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Here is some string: $someString")
        subscribeObservers()
        viewModel.setStateEvent(mainStateEvent = MainStateEvent.GetBlogEvents)
    }


    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitles(dataState.data)
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


    private fun displayError(message: String?) {
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
    }

    private fun appendBlogTitles(blogs: List<Blog>) { // write titles
    }
}