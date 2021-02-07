package com.softex.gtec.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.softex.gtec.R
import com.softex.gtec.databinding.FragmentRegisterBinding
import com.softex.gtec.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RegisterFragment : BaseFragment(R.layout.fragment_register) {

    private val viewModel: RegisterViewModel by viewModels()

    private var _binding: FragmentRegisterBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViews() {
    }

    override fun subscribeObservers() {
    }
}