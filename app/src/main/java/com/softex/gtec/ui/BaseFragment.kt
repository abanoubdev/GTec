package com.softex.gtec.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

abstract class BaseFragment(layoutId: Int) : Fragment(layoutId) {

    abstract fun initViews()

    abstract fun subscribeObservers()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        subscribeObservers()
    }

    protected fun displayLoadingDialog(isLoading: Boolean) {

    }
}