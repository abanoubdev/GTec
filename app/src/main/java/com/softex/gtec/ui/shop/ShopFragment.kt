package com.softex.gtec.ui.shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.softex.gtec.R
import com.softex.gtec.databinding.ShopFragmentBinding
import com.softex.gtec.model.featuredImages.BannerResponseItem
import com.softex.gtec.model.menuItems.NavigationMenuResponseItem
import com.softex.gtec.model.newArrivals.NewArrivalsResponseItem
import com.softex.gtec.model.topCategories.TopCategoriesResponseItem
import com.softex.gtec.ui.BaseFragment
import com.softex.gtec.ui.shop.adapter.banners.BannersAdapter
import com.softex.gtec.ui.shop.adapter.topCategories.TopCategoriesAdapter
import com.softex.gtec.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ShopFragment : BaseFragment(R.layout.shop_fragment), View.OnClickListener,
    TopCategoriesAdapter.TopCategoriesItemClickListener, BannersAdapter.BannerItemClickListener {

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
        viewModel.topCategoriesDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<TopCategoriesResponseItem>?> -> {
                    displayLoadingDialog(false)
                    _binding?.recyclerTopCategories?.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    _binding?.recyclerTopCategories?.isNestedScrollingEnabled = true
                    _binding?.recyclerTopCategories?.setHasFixedSize(true)
                    _binding?.recyclerTopCategories?.adapter = dataState.data?.let {
                        TopCategoriesAdapter(
                            it, this@ShopFragment
                        )
                    }

                    val size = dataState.data?.size
                    _binding?.tvSeeAll?.text = getString(R.string.see_all_sample) + " ($size) "
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

        viewModel.bannerDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<BannerResponseItem>?> -> {
                    displayLoadingDialog(false)
                    _binding?.recyclerBanners?.layoutManager = LinearLayoutManager(
                        context,
                        LinearLayoutManager.HORIZONTAL, false
                    )
                    _binding?.recyclerBanners?.adapter = dataState.data?.let {
                        BannersAdapter(
                            it, this@ShopFragment
                        )
                    }
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

        viewModel.electronicsDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<NewArrivalsResponseItem>?> -> {
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

        viewModel.homeApplianceDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<NewArrivalsResponseItem>?> -> {
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

        viewModel.setStateEvent(ShopStateEvent.Shop)
    }

    private val viewModel: ShopViewModel by viewModels()

    override fun onClick(v: View?) {
        if (v == _binding?.tvSeeAll) {
            Log.d("See All", "Pressed")
        }
    }

    override fun onTopCategoryItemCLicked(item: TopCategoriesResponseItem) {
    }

    override fun onBannerItemClicked(item: BannerResponseItem) {
    }
}