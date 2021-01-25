package com.softex.gtec.repository

import com.softex.gtec.model.Blog
import com.softex.gtec.retrofit.BlogRetrofit
import com.softex.gtec.retrofit.NetworkMapper
import com.softex.gtec.room.BlogDao
import com.softex.gtec.room.CacheMapper
import com.softex.gtec.util.DataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {

    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        delay(1000) // delay is just seeing the progress
        try {
            // retrieve from network
            val networkBlogList = blogRetrofit.getBlogs()
            val blogList = networkMapper.mapFromEntityList(networkBlogList)

            // send to cache
            for (blog in blogList) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }

            // read from the cache
            val cachedBlogList = blogDao.getBlogList()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogList)))

        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}