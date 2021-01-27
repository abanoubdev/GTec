package com.softex.gtec.repository

import com.softex.gtec.model.User
import com.softex.gtec.util.DataState
import kotlinx.coroutines.flow.Flow

interface RepositorySource {
    suspend fun login(username: String, password: String): Flow<DataState<User>>
}