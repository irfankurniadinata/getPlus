package com.example.testgetplus.domain.repository

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Home
import com.example.testgetplus.data.model.Layout
import kotlinx.coroutines.flow.Flow

interface HomepageRepository {
    suspend fun getHomepage(): Flow<BaseResponse<Home>>
}