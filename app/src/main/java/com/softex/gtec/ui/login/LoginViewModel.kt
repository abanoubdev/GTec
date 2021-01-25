package com.softex.gtec.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.softex.gtec.repository.MainRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class LoginViewModel
@ViewModelInject
constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

}