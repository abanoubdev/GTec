package com.softex.gtec.ui.register

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.softex.gtec.repository.RepositorySource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class RegisterViewModel
@ViewModelInject
constructor(
    private val mainRepository: RepositorySource
) : ViewModel() {
}