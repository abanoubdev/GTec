package com.softex.gtec.ui.homepage

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softex.gtec.model.User
import com.softex.gtec.model.menuItems.NavigationMenuResponseItem
import com.softex.gtec.repository.MainRepository
import com.softex.gtec.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@HiltViewModel
class HomepageViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<User?>> = MutableLiveData()

    val dataState: LiveData<DataState<User?>>
        get() = _dataState

    private val _menuItemsDataState: MutableLiveData<DataState<List<NavigationMenuResponseItem>?>> =
        MutableLiveData()
    val menuItemsDataState: LiveData<DataState<List<NavigationMenuResponseItem>?>>
        get() = _menuItemsDataState

    fun setStateEvent(event: HomepageStateEvent) {
        when (event) {
            is HomepageStateEvent.GetData -> {
                viewModelScope.launch {
                    getCachedUser()
                    mainRepository.getMenuItems()
                        .onEach { dataState ->
                            _menuItemsDataState.value = dataState
                        }.launchIn(viewModelScope)
                }
            }
        }
    }

    private fun getCachedUser() {
        viewModelScope.launch {
            mainRepository.getCachedUser().onEach { dataState ->
                _dataState.value = dataState
            }.launchIn(viewModelScope)
        }
    }

    fun reset() {
        viewModelScope.launch {
            mainRepository.getCachedUser().onEach { dataState ->
                mainRepository.reset()
            }.launchIn(viewModelScope)
        }
    }
}