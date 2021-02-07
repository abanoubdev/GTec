package com.softex.gtec.ui.homepage

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.softex.gtec.repository.RepositorySource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomepageViewModel
@ViewModelInject
constructor(
    private val mainRepository: RepositorySource
) : ViewModel() {

}