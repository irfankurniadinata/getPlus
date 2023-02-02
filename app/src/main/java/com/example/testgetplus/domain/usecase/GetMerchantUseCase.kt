package com.example.testgetplus.domain.usecase

import com.example.testgetplus.core.BaseResponse
import com.example.testgetplus.data.model.Merchant
import com.example.testgetplus.domain.repository.MerchantRepository
import kotlinx.coroutines.flow.Flow

class GetMerchantUseCase(private val merchantRepository: MerchantRepository) {
    suspend fun execute(queryMap: HashMap<String, Any?>?): Flow<BaseResponse<Merchant>> {
        return merchantRepository.getMerchant(queryMap)
    }
}