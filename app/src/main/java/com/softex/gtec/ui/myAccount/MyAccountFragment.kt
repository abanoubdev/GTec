package com.softex.gtec.ui.myAccount

import androidx.fragment.app.viewModels
import com.softex.gtec.R
import com.softex.gtec.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MyAccountFragment : BaseFragment(R.layout.my_account_fragment) {


    override fun initViews() {
    }

    override fun subscribeObservers() {
    }

    private val viewModel: MyAccountViewModel by viewModels()
}