package com.softex.gtec.ui.shop

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softex.gtec.model.featuredImages.BannerResponse
import com.softex.gtec.model.menuItems.NavigationMenuResponse
import com.softex.gtec.model.newArrivals.NewArrivalsResponse
import com.softex.gtec.model.topCategories.TopCategoriesResponse
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

    private val _newArrivalsDataState: MutableLiveData<DataState<NewArrivalsResponse?>> =
        MutableLiveData()
    val newArrivalsDataState: LiveData<DataState<NewArrivalsResponse?>?>
        get() = _newArrivalsDataState

    private val _topCategoriesDataState: MutableLiveData<DataState<TopCategoriesResponse?>> =
        MutableLiveData()
    val topCategoriesDataState: LiveData<DataState<TopCategoriesResponse?>?>
        get() = _topCategoriesDataState

    private val _bannerDataState: MutableLiveData<DataState<BannerResponse?>> = MutableLiveData()
    val bannerDataState: LiveData<DataState<BannerResponse?>>
        get() = _bannerDataState

    private val _menuItemsDataState: MutableLiveData<DataState<NavigationMenuResponse?>> =
        MutableLiveData()
    val menuItemsDataState: LiveData<DataState<NavigationMenuResponse?>>
        get() = _menuItemsDataState

    fun setStateEvent(stateEvent: ShopStateEvent) {
        when (stateEvent) {
            ShopStateEvent.Shop -> {
                viewModelScope.launch {

                    mainRepository.getTopCategories()
                        .onEach { dataState ->
                            _topCategoriesDataState.value = dataState
                        }.launchIn(viewModelScope)

                    mainRepository.getNewArrivals()
                        .onEach { dataState ->
                            _newArrivalsDataState.value = dataState
                        }.launchIn(viewModelScope)

//                    mainRepository.getBanners()
//                        .onEach { dataState ->
//                            _bannerDataState.value = dataState
//                        }.launchIn(viewModelScope)

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