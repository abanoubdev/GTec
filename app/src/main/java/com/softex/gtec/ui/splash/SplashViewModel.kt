package com.softex.gtec.ui.splash

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.softex.gtec.repository.RepositorySource
import com.softex.gtec.util.SaltEncryption
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class SplashViewModel
@ViewModelInject
constructor(
    private val mainRepository: RepositorySource
) : ViewModel() {
}