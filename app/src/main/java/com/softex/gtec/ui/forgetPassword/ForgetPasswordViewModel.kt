package com.softex.gtec.ui.forgetPassword

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.softex.gtec.extensions.isEmpty
import com.softex.gtec.extensions.isValidEmail
import com.softex.gtec.model.ForgetPasswordResponse
import com.softex.gtec.model.User
import com.softex.gtec.repository.RepositorySource
import com.softex.gtec.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception

@ExperimentalCoroutinesApi
@HiltViewModel
class ForgetPasswordViewModel
@ViewModelInject
constructor(
    private val mainRepository: RepositorySource
) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<ForgetPasswordResponse?>> = MutableLiveData()

    val dataState: LiveData<DataState<ForgetPasswordResponse?>>
        get() = _dataState

    private val _errorState: MutableLiveData<ForgetPasswordStateEvent> = MutableLiveData()
    val errorState: MutableLiveData<ForgetPasswordStateEvent>
        get() = _errorState

    var email: String = ""

    fun setStateEvent(event: ForgetPasswordStateEvent.ForgetPassword) {
        viewModelScope.launch {
            when (event) {
                is ForgetPasswordStateEvent.ForgetPassword -> {
                    if (email.isEmpty()) {
                        _errorState.value = ForgetPasswordStateEvent.EmptyEmail
                        return@launch
                    }

                    if (!email.isValidEmail()) {
                        _errorState.value = ForgetPasswordStateEvent.InvalidEmail
                        return@launch
                    }

                    mainRepository.forgetPassword(email)
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