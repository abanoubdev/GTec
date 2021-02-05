package com.softex.gtec.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softex.gtec.model.User
import com.softex.gtec.repository.RepositorySource
import com.softex.gtec.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class SplashViewModel
@Inject
constructor(
    private val mainRepository: RepositorySource
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<User?>> = MutableLiveData()

    val dataState: LiveData<DataState<User?>>
        get() = _dataState

    fun setStateEvent(splashStateEvent: SplashStateEvent) {
        viewModelScope.launch {
            when (splashStateEvent) {
                is SplashStateEvent.GetUser -> {
                    mainRepository.getCachedUser()
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)
                }
            }
        }
    }
}