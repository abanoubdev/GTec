package com.softex.gtec.ui.order

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.softex.gtec.repository.RepositorySource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@HiltViewModel
class OrderViewModel
@ViewModelInject
constructor(
    private val mainRepository: RepositorySource
) : ViewModel() {

}