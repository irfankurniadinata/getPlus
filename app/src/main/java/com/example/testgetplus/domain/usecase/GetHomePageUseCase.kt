package com.example.testgetplus.domain.usecase

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Home
import com.example.testgetplus.data.model.Layout
import com.example.testgetplus.domain.repository.HomepageRepository
import kotlinx.coroutines.flow.Flow

class GetHomePageUseCase(private val homepageRepository: HomepageRepository) {
    suspend fun execute(): Flow<BaseResponse<Home>> {
        return homepageRepository.getHomepage()
    }
}