package com.softex.gtec.repository

import com.softex.gtec.BuildConfig
import com.softex.gtec.extensions.encrypt
import com.softex.gtec.model.User
import com.softex.gtec.retrofit.RetrofitService
import com.softex.gtec.room.UserDao
import com.softex.gtec.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    private val userDao: UserDao,
    private val retrofitService: RetrofitService
) : RepositorySource {

    override suspend fun login(username: String, password: String):
            Flow<DataState<User?>> = flow {
        emit(DataState.Loading)
        try {
            val user =
                retrofitService.mobileLogin(
                    BuildConfig.security_string,
                    BuildConfig.server_ip,
                    BuildConfig.database_name,
                    BuildConfig.encrypted_ex_app_id,
                    username,
                    password.encrypt()
                )

            if (user?.CustomerID != null) {
                userDao.insert(user)
                emit(DataState.Success(user))
            } else {
                emit(DataState.Error(Exception()))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun getCachedUser(): Flow<DataState<User?>> = flow {
        emit(DataState.Loading)
        try {
            val cachedList =
                userDao.getUser()
            if (cachedList.isNotEmpty()) {
                emit(DataState.Success(cachedList[0]))
            } else {
                emit(DataState.Success(null))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}