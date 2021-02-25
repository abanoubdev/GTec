package com.softex.gtec.repository

import com.softex.gtec.BuildConfig
import com.softex.gtec.extensions.encrypt
import com.softex.gtec.model.User
import com.softex.gtec.model.featuredImages.BannerResponseItem
import com.softex.gtec.model.menuItems.NavigationMenuResponseItem
import com.softex.gtec.model.newArrivals.NewArrivalsResponseItem
import com.softex.gtec.model.topCategories.TopCategoriesResponseItem
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
                retrofitService.customerLogin(
                    BuildConfig.security_string,
                    BuildConfig.server_ip,
                    BuildConfig.database_name,
                    BuildConfig.encrypted_ex_app_id,
                    BuildConfig.encrypted_app_url.encrypt(),
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
        val cachedList =
            userDao.getUser()
        if (cachedList.isNotEmpty()) {
            emit(DataState.Success(cachedList[0]))
        } else {
            emit(DataState.Success(null))
        }
    }

    override suspend fun getElectronics(): Flow<DataState<List<NewArrivalsResponseItem>?>> = flow {
        emit(DataState.Loading)
        val treeNodeID = 19

        val electronics = retrofitService.getNewArrivals(
            BuildConfig.security_string,
            BuildConfig.server_ip,
            BuildConfig.database_name,
            BuildConfig.encrypted_ex_app_id,
            treeNodeID.toString().encrypt()
        )

        if (electronics != null && electronics.isNotEmpty()) {
            emit(DataState.Success(electronics))
        } else {
            emit(DataState.Error(Exception("Electronics is Empty")))
        }
    }

    override suspend fun getHomeAppliance(): Flow<DataState<List<NewArrivalsResponseItem>?>> =
        flow {
            emit(DataState.Loading)
            val treeNodeID = 20

            val electronics = retrofitService.getNewArrivals(
                BuildConfig.security_string,
                BuildConfig.server_ip,
                BuildConfig.database_name,
                BuildConfig.encrypted_ex_app_id,
                treeNodeID.toString().encrypt()
            )

            if (electronics != null && electronics.isNotEmpty()) {
                emit(DataState.Success(electronics))
            } else {
                emit(DataState.Error(Exception("Home Appliance is Empty")))
            }
        }

    override suspend fun getBanners(): Flow<DataState<List<BannerResponseItem>?>> = flow {
        emit(DataState.Loading)
        val treeNodeId = 5

        val banners = retrofitService.getBanners(
            BuildConfig.security_string,
            BuildConfig.server_ip,
            BuildConfig.database_name,
            BuildConfig.encrypted_ex_app_id,
            treeNodeId.toString().encrypt()
        )

        if (banners != null && banners.isNotEmpty()) {
            emit(DataState.Success(banners))
        } else {
            emit(DataState.Error(Exception("Banners are empty")))
        }
    }

    override suspend fun getMenuItems(): Flow<DataState<List<NavigationMenuResponseItem>?>> = flow {
        emit(DataState.Loading)
        val classificationID = 2

        val banners = retrofitService.getCategoriesWithClassification(
            BuildConfig.security_string,
            BuildConfig.server_ip,
            BuildConfig.database_name,
            BuildConfig.encrypted_ex_app_id,
            classificationID.toString().encrypt()
        )

        if (banners != null && banners.isNotEmpty()) {
            emit(DataState.Success(banners))
        } else {
            emit(DataState.Error(Exception("Menu Items are empty")))
        }
    }

    override suspend fun getTopCategories(): Flow<DataState<List<TopCategoriesResponseItem>?>> =
        flow {
            emit(DataState.Loading)
            val encryptedTreeNode = 18

            val topCategories = retrofitService.getTopCategories(
                BuildConfig.security_string,
                BuildConfig.server_ip,
                BuildConfig.database_name,
                BuildConfig.encrypted_ex_app_id,
                encryptedTreeNode.toString().encrypt()
            )

            if (!topCategories.isNullOrEmpty()) {
                emit(DataState.Success(topCategories))
            } else {
                emit(DataState.Error(Exception("Top Categories are empty")))
            }
        }
}