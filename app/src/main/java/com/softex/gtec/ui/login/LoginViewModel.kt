package com.softex.gtec.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softex.gtec.model.User
import com.softex.gtec.repository.MainRepository
import com.softex.gtec.util.DataState
import com.softex.gtec.util.SaltEncryption
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<User>> = MutableLiveData()

    var username: String = ""
    var password: String = ""

    val dataState: LiveData<DataState<User>>
        get() = _dataState

    fun setStateEvent(loginStateEvent: LoginStateEvent) {
        viewModelScope.launch {
            when (loginStateEvent) {
                is LoginStateEvent.Login -> {
                    mainRepository.login(username, SaltEncryption.encrypt(password))
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)
                }
            }
        }
    }
}