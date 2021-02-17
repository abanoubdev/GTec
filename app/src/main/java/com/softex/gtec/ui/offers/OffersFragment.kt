package com.softex.gtec.ui.offers

import androidx.fragment.app.viewModels
import com.softex.gtec.R
import com.softex.gtec.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class OffersFragment : BaseFragment(R.layout.offers_fragment) {


    override fun initViews() {
        
    }

    override fun subscribeObservers() {
        
    }

    private val viewModel: OffersViewModel by viewModels()
}