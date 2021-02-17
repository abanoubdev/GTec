package com.softex.gtec.ui.shop

import androidx.fragment.app.viewModels
import com.softex.gtec.R
import com.softex.gtec.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ShopFragment : BaseFragment(R.layout.shop_fragment) {

    override fun initViews() {
    }

    override fun subscribeObservers() {
    }

    private val viewModel: ShopViewModel by viewModels()
}