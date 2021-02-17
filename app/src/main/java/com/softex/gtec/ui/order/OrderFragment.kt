package com.softex.gtec.ui.order

import androidx.fragment.app.viewModels
import com.softex.gtec.R
import com.softex.gtec.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class OrderFragment : BaseFragment(R.layout.order_fragment) {

    override fun initViews() {
    }

    override fun subscribeObservers() {
    }

    private val viewModel: OrderViewModel by viewModels()
}