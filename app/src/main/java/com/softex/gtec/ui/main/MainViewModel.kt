package com.softex.gtec.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.softex.gtec.model.User
import com.softex.gtec.repository.MainRepository
import com.softex.gtec.repository.RepositorySource
import com.softex.gtec.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MainViewModel
@ViewModelInject
constructor(
    private val mainRepository: RepositorySource
) : ViewModel() {
}