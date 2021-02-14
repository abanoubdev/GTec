package com.softex.gtec.ui.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softex.gtec.R
import com.softex.gtec.databinding.FragmentHomepageBinding
import com.softex.gtec.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomepageFragment : BaseFragment(R.layout.fragment_homepage),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private var _binding: FragmentHomepageBinding? = null

    private val binding get() = _binding!!

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
        _binding?.bottomNavigationView?.background = null
        _binding?.bottomNavigationView?.menu?.getItem(2)
            ?.isEnabled = false
        _binding?.bottomNavigationView?.setOnNavigationItemSelectedListener(this)
    }

    override fun subscribeObservers() {
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when (p0.itemId) {
            R.id.home -> {

            }

            R.id.my_account -> {

            }

            R.id.offers -> {

            }

            R.id.order -> {

            }
        }

        return true
    }
}