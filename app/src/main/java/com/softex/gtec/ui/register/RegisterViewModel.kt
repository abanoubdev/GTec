package com.softex.gtec.ui.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softex.gtec.extensions.isEmpty
import com.softex.gtec.extensions.isValidEmail
import com.softex.gtec.model.User
import com.softex.gtec.repository.RepositorySource
import com.softex.gtec.ui.login.LoginStateEvent
import com.softex.gtec.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception

@ExperimentalCoroutinesApi
@HiltViewModel
class RegisterViewModel
@ViewModelInject
constructor(
    private val mainRepository: RepositorySource
) : ViewModel() {

    var name: String = ""
    var usernameOrEmail: String = ""
    var password: String = ""

    private val _errorState: MutableLiveData<RegisterStateEvent> = MutableLiveData()

    val errorState: MutableLiveData<RegisterStateEvent>
        get() = _errorState

    private val _dataState: MutableLiveData<DataState<Int?>> = MutableLiveData()

    val dataState: LiveData<DataState<Int?>>
        get() = _dataState

    fun setStateEvent(stateEvent: RegisterStateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is RegisterStateEvent.Register -> {
                    if (name.isEmpty()) {
                        _errorState.value = RegisterStateEvent.InvalidName
                        return@launch
                    }

                    if (usernameOrEmail.isEmpty()) {
                        _errorState.value = RegisterStateEvent.InvalidUsernameOrEmail
                        return@launch
                    }

                    if (password.isEmpty()) {
                        _errorState.value = RegisterStateEvent.InvalidPassword
                        return@launch
                    }

                    mainRepository.register(name, usernameOrEmail, password)
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