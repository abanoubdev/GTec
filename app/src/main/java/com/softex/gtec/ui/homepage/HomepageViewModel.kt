package com.softex.gtec.ui.homepage

import androidx.lifecycle.*
import com.softex.gtec.repository.RepositorySource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomepageViewModel
@Inject
constructor(
    private val mainRepository: RepositorySource
) : ViewModel() {

}