package com.softex.gtec.ui.shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.softex.gtec.R
import com.softex.gtec.databinding.ShopFragmentBinding
import com.softex.gtec.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ShopFragment : BaseFragment(R.layout.shop_fragment), View.OnClickListener {

    private var _binding: ShopFragmentBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ShopFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initViews() {
        _binding?.tvSeeAll?.setOnClickListener(this)
    }

    override fun subscribeObservers() {

        viewModel.bannerDataState.observe(viewLifecycleOwner, {

        })

        viewModel.newArrivalsDataState.observe(viewLifecycleOwner, {

        })

        viewModel.menuItemsState.observe(viewLifecycleOwner, {

        })

        viewModel.setStateEvent(ShopStateEvent.Shop)
    }

    private val viewModel: ShopViewModel by viewModels()

    override fun onClick(v: View?) {
        if (v == _binding?.tvSeeAll) {
            Log.d("See All", "Pressed")
        }
    }
}