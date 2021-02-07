package com.softex.gtec.ui.forgetPassword

import androidx.fragment.app.viewModels
import com.softex.gtec.R
import com.softex.gtec.ui.BaseFragment
import com.softex.gtec.ui.homepage.HomepageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ForgetPasswordFragment : BaseFragment(R.layout.fragment_forget_password) {

    private val viewModel: HomepageViewModel by viewModels()
    
    override fun initViews() {
    }

    override fun subscribeObservers() {
       
    }
}