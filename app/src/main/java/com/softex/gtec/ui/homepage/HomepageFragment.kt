package com.softex.gtec.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.softex.gtec.R
import com.softex.gtec.databinding.FragmentHomepageBinding
import com.softex.gtec.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomepageFragment : BaseFragment(R.layout.fragment_homepage) {

    private var _binding: FragmentHomepageBinding? = null

    private val binding get() = _binding!!

    private val TAG: String = "HomepageFragment"

    private val viewModel: HomepageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomepageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViews() {
    }

    override fun subscribeObservers() {
    }
}