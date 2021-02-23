package com.softex.gtec.ui.shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.softex.gtec.R
import com.softex.gtec.databinding.ShopFragmentBinding
import com.softex.gtec.model.featuredImages.BannerResponse
import com.softex.gtec.model.menuItems.NavigationMenuResponse
import com.softex.gtec.model.newArrivals.NewArrivalsResponse
import com.softex.gtec.ui.BaseFragment
import com.softex.gtec.util.DataState
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

        viewModel.bannerDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<BannerResponse?> -> {
                    displayLoadingDialog(false)
                    Log.d("newArrivals", dataState.data.toString())
                }

                is DataState.Error -> {
                    displayLoadingDialog(false)
                    Log.d("newArrivals", dataState.exception.toString())
                }

                is DataState.Loading -> {
                    displayLoadingDialog(true)
                }
            }
        })

        viewModel.newArrivalsDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<NewArrivalsResponse?> -> {
                    displayLoadingDialog(false)
                    Log.d("newArrivals", dataState.data.toString())
                }

                is DataState.Error -> {
                    displayLoadingDialog(false)
                    Log.d("newArrivals", dataState.exception.toString())
                }

                is DataState.Loading -> {
                    displayLoadingDialog(true)
                }
            }
        })

        viewModel.menuItemsDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<NavigationMenuResponse?> -> {
                    displayLoadingDialog(false)
                    Log.d("newArrivals", dataState.data.toString())
                }

                is DataState.Error -> {
                    displayLoadingDialog(false)
                    Log.d("newArrivals", dataState.exception.toString())
                }

                is DataState.Loading -> {
                    displayLoadingDialog(true)
                }
            }
        })

//        viewModel.setStateEvent(ShopStateEvent.Shop)
    }

    private val viewModel: ShopViewModel by viewModels()

    override fun onClick(v: View?) {
        if (v == _binding?.tvSeeAll) {
            Log.d("See All", "Pressed")
        }
    }
}