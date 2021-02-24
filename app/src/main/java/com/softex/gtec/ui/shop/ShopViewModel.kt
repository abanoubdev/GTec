package com.softex.gtec.ui.shop

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softex.gtec.model.featuredImages.BannerResponseItem
import com.softex.gtec.model.menuItems.NavigationMenuResponseItem
import com.softex.gtec.model.newArrivals.NewArrivalsResponseItem
import com.softex.gtec.model.topCategories.TopCategoriesResponseItem
import com.softex.gtec.repository.RepositorySource
import com.softex.gtec.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception

@ExperimentalCoroutinesApi
@HiltViewModel
class ShopViewModel
@ViewModelInject
constructor(
    private val mainRepository: RepositorySource
) : ViewModel() {

    private val _electronicsDataState: MutableLiveData<DataState<List<NewArrivalsResponseItem>?>> =
        MutableLiveData()
    val electronicsDataState: LiveData<DataState<List<NewArrivalsResponseItem>?>?>
        get() = _electronicsDataState

    private val _homeApplianceDataState: MutableLiveData<DataState<List<NewArrivalsResponseItem>?>> =
        MutableLiveData()
    val homeApplianceDataState: LiveData<DataState<List<NewArrivalsResponseItem>?>?>
        get() = _homeApplianceDataState

    private val _topCategoriesDataState: MutableLiveData<DataState<List<TopCategoriesResponseItem>?>> =
        MutableLiveData()
    val topCategoriesDataState: LiveData<DataState<List<TopCategoriesResponseItem>?>?>
        get() = _topCategoriesDataState

    private val _bannerDataState: MutableLiveData<DataState<List<BannerResponseItem>?>> = MutableLiveData()
    val bannerDataState: LiveData<DataState<List<BannerResponseItem>?>>
        get() = _bannerDataState

    private val _menuItemsDataState: MutableLiveData<DataState<List<NavigationMenuResponseItem>?>> =
        MutableLiveData()
    val menuItemsDataState: LiveData<DataState<List<NavigationMenuResponseItem>?>>
        get() = _menuItemsDataState

    fun setStateEvent(stateEvent: ShopStateEvent) {
        when (stateEvent) {
            ShopStateEvent.Shop -> {
                viewModelScope.launch {

                    mainRepository.getTopCategories()
                        .onEach { dataState ->
                            _topCategoriesDataState.value = dataState
                        }.launchIn(viewModelScope)

                    mainRepository.getElectronics()
                        .onEach { dataState ->
                            _electronicsDataState.value = dataState
                        }.launchIn(viewModelScope)

                    mainRepository.getHomeAppliance()
                        .onEach { dataState ->
                            _homeApplianceDataState.value = dataState
                        }.launchIn(viewModelScope)

                    mainRepository.getBanners()
                        .onEach { dataState ->
                            _bannerDataState.value = dataState
                        }.launchIn(viewModelScope)

                    mainRepository.getMenuItems()
                        .onEach { dataState ->
                            _menuItemsDataState.value = dataState
                        }.launchIn(viewModelScope)
                }
            }
            else -> {
                throw Exception("Unknown State")
            }
        }
    }
}