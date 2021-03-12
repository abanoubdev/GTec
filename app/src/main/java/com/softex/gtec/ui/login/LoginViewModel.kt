package com.softex.gtec.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softex.gtec.extensions.isEmpty
import com.softex.gtec.model.User
import com.softex.gtec.repository.MainRepository
import com.softex.gtec.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception

@ExperimentalCoroutinesApi
@HiltViewModel
class LoginViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<User?>> = MutableLiveData()

    val dataState: LiveData<DataState<User?>>
        get() = _dataState

    var username: String = ""
    var password: String = ""

    private val _errorState: MutableLiveData<LoginStateEvent> = MutableLiveData()
    val errorState: MutableLiveData<LoginStateEvent>
        get() = _errorState

    fun setStateEvent(loginStateEvent: LoginStateEvent) {
        viewModelScope.launch {
            when (loginStateEvent) {
                is LoginStateEvent.Login -> {
                    if (username.isEmpty()) {
                        _errorState.value = LoginStateEvent.InvalidUsername
                        return@launch
                    }

                    if (password.isEmpty()) {
                        _errorState.value = LoginStateEvent.InvalidPassword
                        return@launch
                    }

                    mainRepository.login(username, password)
                        .onEach { dataState ->
                            _dataState.value = dataState
                        }.launchIn(viewModelScope)
                }
                else -> {
                    throw Exception("State not found")
                }
            }
        }
    }
}