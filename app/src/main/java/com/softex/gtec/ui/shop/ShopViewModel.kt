package com.softex.gtec.ui.shop

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softex.gtec.model.featuredImages.BannerResponse
import com.softex.gtec.model.menuItems.NavigationMenuResponse
import com.softex.gtec.model.newArrivals.NewArrivalsResponse
import com.softex.gtec.repository.RepositorySource
import com.softex.gtec.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@HiltViewModel
class ShopViewModel
@ViewModelInject
constructor(
    private val mainRepository: RepositorySource
) : ViewModel() {

    private val _newArrivalsDataState: MutableLiveData<DataState<NewArrivalsResponse?>> =
        MutableLiveData()
    val newArrivalsDataState: LiveData<DataState<NewArrivalsResponse?>>
        get() = _newArrivalsDataState

    private val _bannerDataState: MutableLiveData<DataState<BannerResponse?>> = MutableLiveData()
    val bannerDataState: LiveData<DataState<BannerResponse?>>
        get() = _bannerDataState

    private val _menuItemsState: MutableLiveData<DataState<NavigationMenuResponse?>> = MutableLiveData()
    val menuItemsState: LiveData<DataState<NavigationMenuResponse?>>
        get() = _menuItemsState

    fun setStateEvent(stateEvent: ShopStateEvent) {
        when (stateEvent) {
            ShopStateEvent.Shop -> {
                viewModelScope.launch {
                    mainRepository.getNewArrivals()
                        .onEach { dataState ->
                            _newArrivalsDataState.value = dataState
                        }.launchIn(viewModelScope)

                    mainRepository.getBanners()
                        .onEach { dataState ->
                            _bannerDataState.value = dataState
                        }.launchIn(viewModelScope)

                    mainRepository.getMenuItems()
                        .onEach { dataState ->
                            _menuItemsState.value = dataState
                        }.launchIn(viewModelScope)
                }
            }
        }
    }
}