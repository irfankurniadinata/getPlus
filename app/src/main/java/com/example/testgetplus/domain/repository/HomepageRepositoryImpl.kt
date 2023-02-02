package com.example.testgetplus.domain.repository

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Home
import com.example.testgetplus.data.model.Layout
import com.example.testgetplus.data.remote.HomepageDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomepageRepositoryImpl(private val homepageDataSource: HomepageDataSource) : HomepageRepository {
    override suspend fun getHomepage(): Flow<BaseResponse<Home>> {
        return flow {
            val response = homepageDataSource.getHomepage()
            if (response.isSuccessful) {
                val body = response.body()
                emit(body!!)
            }
        }
    }
}